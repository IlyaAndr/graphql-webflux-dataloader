package com.yg.auditservice

data class AuditRecord(val id: String, val customerId: Long, val description: String) {
    data class CreationRequest(val customerId: Long, val description: String)
}