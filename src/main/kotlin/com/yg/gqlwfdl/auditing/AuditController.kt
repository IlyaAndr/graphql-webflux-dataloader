package com.yg.gqlwfdl.auditing

import com.yg.gqlwfdl.queuing.QueueController
import org.springframework.stereotype.Controller

@Controller
class AuditController(private val queueController: QueueController) {
    fun requestAuditRecordCreation(request: AuditRecord.CreationRequest) =
            queueController.sendMessageToQueue("audit", request)
}