package com.yg.gqlwfdl.services

import com.yg.gqlwfdl.dataaccess.CompanyRepository
import com.yg.gqlwfdl.dataaccess.EntityRequestInfo
import org.springframework.stereotype.Service

/**
 * Service for handling functionality related to companies. Communicates with the data access layer to get the data
 * from the database, and exposes it to callers using the domain model objects (specifically, [Company]). Performs
 * all actions asynchronously.
 */
interface CompanyService {
    /**
     * Returns all [Company] objects.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findAll(requestInfo: EntityRequestInfo? = null): List<Company>

    /**
     * Returns all [Company] objects with the passed in IDs.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo? = null): List<Company>
}

/**
 * Concrete implementation of [see CompanyService]
 */
@Service
class DefaultCompanyService(private val companyRepository: CompanyRepository) : CompanyService {

    override suspend fun findAll(requestInfo: EntityRequestInfo?) = companyRepository.findAll(requestInfo)

    override suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo?) =
            companyRepository.findByIds(ids, requestInfo)
}