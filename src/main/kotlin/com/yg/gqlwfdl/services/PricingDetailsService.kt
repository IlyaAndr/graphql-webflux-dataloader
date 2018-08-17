package com.yg.gqlwfdl.services

import com.yg.gqlwfdl.dataaccess.EntityRequestInfo
import com.yg.gqlwfdl.dataaccess.PricingDetailsRepository
import org.springframework.stereotype.Service

/**
 * Service for handling functionality related to pricing details.  Communicates with the data access layer to get
 * the data from the database, and exposes it to callers using the domain model objects (specifically,
 * [PricingDetails]). Performs all actions asynchronously.
 */
interface PricingDetailsService {
    /**
     * Returns all [PricingDetails] objects.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findAll(requestInfo: EntityRequestInfo? = null): List<PricingDetails>

    /**
     * Returns all [PricingDetails] objects with the passed in IDs.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo? = null): List<PricingDetails>
}

/**
 * Concrete implementation of [see PricingDetailsService]
 */
@Service
class DefaultPricingDetailsService(private val pricingDetailsRepository: PricingDetailsRepository)
    : PricingDetailsService {

    override suspend fun findAll(requestInfo: EntityRequestInfo?) = pricingDetailsRepository.findAll(requestInfo)

    override suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo?) =
            pricingDetailsRepository.findByIds(ids, requestInfo)
}
