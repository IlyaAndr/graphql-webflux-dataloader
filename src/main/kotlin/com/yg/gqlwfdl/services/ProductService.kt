package com.yg.gqlwfdl.services

import com.yg.gqlwfdl.dataaccess.EntityRequestInfo
import com.yg.gqlwfdl.dataaccess.ProductRepository
import org.springframework.stereotype.Service

/**
 * Service for handling functionality related to companies. Communicates with the data access layer to get the data
 * from the database, and exposes it to callers using the domain model objects (specifically, [Product]). Performs
 * all actions asynchronously.
 */
interface ProductService {
    /**
     * Returns all [Product] objects.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findAll(requestInfo: EntityRequestInfo? = null): List<Product>

    /**
     * Returns all [Product] objects with the passed in IDs.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo? = null): List<Product>

    /**
     * Returns all [Product] objects with the passed in IDs, along with their order counts, wrapped in an
     * [EntityWithCount] object.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findWithOrderCount(ids: List<Long>, requestInfo: EntityRequestInfo? = null): List<EntityWithCount<Long, Product>>

    /**
     * Gets the top [count] best selling products.
     *
     * @param requestInfo Information about the request, such as the fields of the entity which were requested by the
     * client, if the call was made from the context of a client request.
     */
    suspend fun findTopSelling(count: Int, requestInfo: EntityRequestInfo?): List<EntityWithCount<Long, Product>>
}

/**
 * Concrete implementation of [see ProductService]
 */
@Service
class DefaultProductService(private val productRepository: ProductRepository) : ProductService {
    override suspend fun findAll(requestInfo: EntityRequestInfo?) = productRepository.findAll(requestInfo)

    override suspend fun findByIds(ids: List<Long>, requestInfo: EntityRequestInfo?) =
            productRepository.findByIds(ids, requestInfo)

    override suspend fun findWithOrderCount(ids: List<Long>, requestInfo: EntityRequestInfo?) =
            productRepository.findWithOrderCount(ids, requestInfo)

    override suspend fun findTopSelling(count: Int, requestInfo: EntityRequestInfo?) =
            productRepository.findTopSelling(count, requestInfo)
}