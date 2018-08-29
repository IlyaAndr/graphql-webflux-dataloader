package com.yg.auditservice.queuing

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.stereotype.Component

@Component
class MessageListenerContainerFactory(private val connectionFactory: ConnectionFactory) {

    fun createMessageListenerContainer(queueName: String): MessageListenerContainer =
            SimpleMessageListenerContainer(connectionFactory).also { it.addQueueNames(queueName) }
}