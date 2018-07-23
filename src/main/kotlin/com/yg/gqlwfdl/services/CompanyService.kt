package com.yg.gqlwfdl.services

import com.yg.gqlwfdl.dataaccess.CompanyRepository
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

/**
 * Service for handling functionality related to companies. Communicates with the data access layer to get the data
 * from the database, and exposes it to callers using the domain model objects (specifically, [Company]). Performs
 * all actions asynchronously.
 */
interface CompanyService {
    /**
     * Returns a [CompletableFuture] which, when completed, will provide a [List] of all [Company] objects.
     *
     * @param env The environment for the current GraphQL data fetch, if this method is called from such a context.
     */
    fun findAll(env: DataFetchingEnvironment? = null): CompletableFuture<List<Company>>

    /**
     * Returns a [CompletableFuture] which, when completed, will provide a [List] of all [Company] objects with the
     * passed in IDs.
     *
     * @param env The environment for the current GraphQL data fetch, if this method is called from such a context.
     */
    fun findByIds(ids: List<Long>, env: DataFetchingEnvironment? = null): CompletableFuture<List<Company>>
}

/**
 * Concrete implementation of [see CompanyService]
 */
@Service
class DefaultCompanyService(private val companyRepository: CompanyRepository) : CompanyService {

    override fun findAll(env: DataFetchingEnvironment?): CompletableFuture<List<Company>> =
            companyRepository.findAll(env)

    override fun findByIds(ids: List<Long>, env: DataFetchingEnvironment?): CompletableFuture<List<Company>> =
            companyRepository.findByIds(ids, env)
}