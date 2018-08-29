package com.yg.auditservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class TestHandler {
    fun textTest(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello world"))
    }

    fun jsonTest(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(listOf(
                        Customer(1, "User", "One", 1, 1),
                        Customer(2, "User", "Two", 1, 1),
                        Customer(3, "User", "Three", 1, 1))))
    }
}