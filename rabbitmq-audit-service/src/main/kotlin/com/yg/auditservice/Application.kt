package com.yg.auditservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application(auditRecordCreationRequestListener: AuditRecordCreationRequestListener) {
    init {
        auditRecordCreationRequestListener.startListening().subscribe {
            println("Audit record created: $it")
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)

//    val webClient = WebClient.create("http://localhost:8081");
//
//    val response = webClient.get()
//            .uri("/textTest")
//            .accept(MediaType.TEXT_PLAIN)
//            .exchange()
//
//    val stringResult = response.flatMap { it.bodyToMono(String::class.java) }.block()
//
//    println(stringResult)
}