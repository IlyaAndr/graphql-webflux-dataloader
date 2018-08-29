package com.yg.auditservice

import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Flux.fromStream
import reactor.core.publisher.Mono
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Repository
class AuditRepository {
    private val DAT_FILE_PATH = Paths.get("./auditrecords.dat")

    fun findAll(): Flux<AuditRecord> =
            DAT_FILE_PATH.linesAsFlux().filter { !it.isNullOrEmpty() }.map { it.toAuditRecord() }

    fun findById(id: String): Mono<AuditRecord> =
            findAll().filter { it.id == id }.next()

    fun create(auditRecord: AuditRecord) : Mono<AuditRecord> {
        DAT_FILE_PATH.toFile().appendText(auditRecord.toDatFileString() + '\n')
        return Mono.just(auditRecord)
    }
}

private fun AuditRecord.toDatFileString() = listOf(id, customerId, description).joinToString(":")

private fun String.toAuditRecord() = with(split(':')) { AuditRecord(get(0), get(1).toLong(), get(2)) }

private fun Path.linesAsFlux() = Flux.using({ Files.lines(this) }, { fromStream(it) }, { it.close() })