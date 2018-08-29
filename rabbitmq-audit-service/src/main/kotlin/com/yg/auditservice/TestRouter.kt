package com.yg.auditservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class TestRouter {

    @Bean
    fun route(testHandler: TestHandler, auditHandler: AuditHandler): RouterFunction<ServerResponse> {
//        return RouterFunctions
//                .route(RequestPredicates.GET("/textTest").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
//                        HandlerFunction<ServerResponse>(testHandler::textTest))
        return router {
            GET("/textTest").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)).invoke(testHandler::textTest)
            GET("/jsonTest").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).invoke(testHandler::jsonTest)
            path("/audit").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).nest {
                GET("/", auditHandler::findAll)
                GET("/{id}", auditHandler::findById)
                POST("/", auditHandler::create)
            }
        }
    }
}