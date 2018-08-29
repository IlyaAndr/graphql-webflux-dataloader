package com.yg.gqlwfdl.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.yg.gqlwfdl.TestDataCreator
import com.yg.gqlwfdl.auditing.AuditController
import com.yg.gqlwfdl.auditing.AuditRecord
import com.yg.gqlwfdl.dataaccess.DBConfig
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.util.concurrent.CompletableFuture
import kotlin.system.measureTimeMillis

@Suppress("unused")
/**
 * Class containing the mutations (e.g. inserts, updates) invoked by GraphQL requests.
 */
class Mutation(private val dbConfig: DBConfig,
               private val auditController: AuditController) : GraphQLMutationResolver {

    /**
     * Deletes all existing data and populates the database with a bunch of randomly generated test data.
     */
    fun createTestData(): String {
        val recordCounts = mutableMapOf<String, Int>()
        val totalTime = measureTimeMillis { recordCounts.putAll(TestDataCreator(dbConfig).execute()) }
        val stringBuilder = StringBuilder("Test data successfully created in ${totalTime}ms")
        recordCounts.toSortedMap(String.CASE_INSENSITIVE_ORDER).forEach {
            stringBuilder.append(" ...").append(it.key).append(": ").append(it.value)
        }
        return stringBuilder.toString()
    }

    fun createAuditRecord(request: AuditRecord.CreationRequest): CompletableFuture<AuditRecord> {
        data class AuditRecordCreationRequest(val customerId: Long, val description: String)

        return WebClient
                .create("http://localhost:8081/audit/")
                .post()
                .body(BodyInserters.fromObject(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono<AuditRecord>()
                .toFuture()
    }

    fun createAuditRecordQueued(request: AuditRecord.CreationRequest): CompletableFuture<String> =
            auditController.requestAuditRecordCreation(request).map { "Audit record creation requested" }.toFuture()
}