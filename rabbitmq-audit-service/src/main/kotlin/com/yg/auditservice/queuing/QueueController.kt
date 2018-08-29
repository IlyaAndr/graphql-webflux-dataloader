package com.yg.auditservice.queuing

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.*
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.annotation.PostConstruct

@Controller
class QueueController(private val amqpTemplate: AmqpTemplate,
                      private val amqpAdmin: AmqpAdmin,
                      private val destinationsConfig: DestinationsConfig,
                      private val messageListenerContainerFactory: MessageListenerContainerFactory) {

    private val jacksonObjectMapper = jacksonObjectMapper()

    @PostConstruct
    fun setupQueueDestinations() {
        destinationsConfig.queues.forEach { _, destination ->
            val exchange = ExchangeBuilder.directExchange(destination.exchange).durable(true).build()
            amqpAdmin.declareExchange(exchange)
            val queue = QueueBuilder.durable(destination.routingKey).build()
            amqpAdmin.declareQueue(queue)
            val binding = BindingBuilder.bind(queue).to(exchange).with(destination.routingKey).noargs()
            amqpAdmin.declareBinding(binding)
        }
    }

    fun <T> receiveMessagesFromQueue(queueName: String, targetClass: Class<T>): Flux<T> {
        val factory = messageListenerContainerFactory.createMessageListenerContainer(getQueue(queueName).routingKey)

        return Flux.create<T> { emitter ->
            factory.setupMessageListener(MessageListener {
                emitter.next(jacksonObjectMapper.readValue(it.body, targetClass))
            })
            emitter.onRequest { factory.start() }
            emitter.onDispose { factory.stop() }
        }
    }

    fun sendMessageToQueue(queueName: String, obj: Any) = sendMessageToQueue(queueName, obj.toQueuePayload())

    private fun getQueue(queueName: String) =
            destinationsConfig.queues[queueName] ?: throw Exception("Queue not found: $queueName")

    fun sendMessageToQueue(queueName: String, payload: String): Mono<Unit> {
        return with(getQueue(queueName)) {
            Mono.fromCallable {
                amqpTemplate.convertAndSend(exchange, routingKey, payload)
            }
        }
    }

    private fun Any.toQueuePayload() = jacksonObjectMapper.writeValueAsString(this)

    private fun <T> String.fromQueuePayload(targetClass: Class<T>) = jacksonObjectMapper.readValue(this, targetClass)
}