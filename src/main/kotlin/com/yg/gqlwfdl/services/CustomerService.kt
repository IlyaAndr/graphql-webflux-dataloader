package com.yg.gqlwfdl.services

import com.yg.gqlwfdl.dataaccess.CustomerRepository
import com.yg.gqlwfdl.dataaccess.EntityRequestInfo
import org.springframework.stereotype.Service

/**
 * Service for handling functionality related to customers. Communicates with the data access layer to get the data
 * from the database, and exposes it to callers using the domain model objects (specifically, [Customer]). Performs
 * all actions asynchronously.
 */
interface CustomerService {
    /**
     * Returns all [Customer]s.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findAll(requestInfo: EntityRequestInfo? = null): List<Customer>

    /**
     * Returns all [Customer]s with the passed in IDs.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo? = null): List<Customer>
}

/**
 * Concrete implementation of [see CustomerService]
 */
@Service
class DefaultCustomerService(private val customerRepository: CustomerRepository)
    : CustomerService {

    override suspend fun findAll(requestInfo: EntityRequestInfo?) = customerRepository.findAll(requestInfo)

    override suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo?) =
            customerRepository.findByIds(ids, requestInfo)
}