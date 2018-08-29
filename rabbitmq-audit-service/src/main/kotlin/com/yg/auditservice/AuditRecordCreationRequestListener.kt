package com.yg.auditservice

import com.yg.auditservice.queuing.QueueController
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.*

@Component
class AuditRecordCreationRequestListener(private val queueController: QueueController,
                                         private val auditRepository: AuditRepository) {

    fun startListening(): Flux<AuditRecord> {
        return queueController.receiveMessagesFromQueue("audit", AuditRecord.CreationRequest::class.java).flatMap {
            auditRepository.create(AuditRecord(UUID.randomUUID().toString(), it.customerId, it.description))
        }
    }
}