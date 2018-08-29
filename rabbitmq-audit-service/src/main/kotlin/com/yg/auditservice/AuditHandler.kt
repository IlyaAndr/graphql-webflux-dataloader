package com.yg.auditservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.util.*

@Component
class AuditHandler(private val auditRepository: AuditRepository) {

    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(auditRepository.findAll(), AuditRecord::class.java)
    }

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        return auditRepository.findById(request.pathVariable("id"))
                .then(ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(auditRepository.findById(request.pathVariable("id")), AuditRecord::class.java))
                .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun create(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(AuditRecord.CreationRequest::class.java).flatMap {
            auditRepository.create(AuditRecord(UUID.randomUUID().toString(), it.customerId, it.description)).flatMap {
                ServerResponse.created(UriComponentsBuilder.fromPath("audit/${it.id}").build().toUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(it))
            }
        }
    }
}