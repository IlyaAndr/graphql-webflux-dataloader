package com.yg.gqlwfdl.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.yg.gqlwfdl.auditing.AuditRecord
import com.yg.gqlwfdl.awaitResult
import com.yg.gqlwfdl.logMessage
import com.yg.gqlwfdl.services.*
import com.yg.gqlwfdl.toEntityRequestInfo
import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.experimental.future.future
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.util.concurrent.CompletableFuture


@Suppress("unused")
/**
 * The resolver for GraphQL queries. Has methods corresponding to the properties on the Query type in the GraphQL
 * schema.
 */
class Query(private val customerService: CustomerService,
            private val companyService: CompanyService,
            private val companyPartnershipService: CompanyPartnershipService,
            private val productService: ProductService,
            private val orderService: OrderService)
    : GraphQLQueryResolver {

    /**
     * Gets all customers in the system.
     */
    fun customers(env: DataFetchingEnvironment): CompletableFuture<List<Customer>> =
            future { customerService.findAll(env.toEntityRequestInfo()) }

    /**
     * Gets all customers with the passed in IDs.
     */
    fun customersByIds(ids: List<Long>, env: DataFetchingEnvironment): CompletableFuture<List<Customer>> =
            future { customerService.findByIds(ids, env.toEntityRequestInfo()) }

    /**
     * Gets all companies in the system.
     */
    fun companies(env: DataFetchingEnvironment) =
            future { companyService.findAll(env.toEntityRequestInfo()) }

    /**
     * Gets all company partnerships in the system.
     */
    fun companyPartnerships(env: DataFetchingEnvironment) =
            future { companyPartnershipService.findAll(env.toEntityRequestInfo()) }

    /**
     * Gets all products in the system.
     */
    fun products(env: DataFetchingEnvironment) =
            future { productService.findAll(env.toEntityRequestInfo()) }

    /**
     * Gets the [count] top-selling products in the system.
     */
    fun topSellingProducts(count: Int, env: DataFetchingEnvironment): CompletableFuture<List<Product>> {
        // Get the top-selling products - this will return a list of EntityWithCount objects (wrapping the Product
        // objects). This will have primed the 'env.context.productOrderCountDataLoader' with the order counts. Convert
        // the returned value back to simple Product objects. The GraphQL libraries will then the ProductResolver
        // for the order count for each product, and it will in turn ask that data loader, which will be able to use
        // the pre-cached values rather than querying again.
        return future {
            productService.findTopSelling(count, env.toEntityRequestInfo())
                    .map { it.entity }
        }
    }

    /**
     * Gets all orders in the system.
     */
    fun orders(env: DataFetchingEnvironment): CompletableFuture<List<Order>> =
            future { orderService.findAll(env.toEntityRequestInfo()) }

    /**
     * Gets all orders with the passed in IDs.
     */
    fun ordersByIds(ids: List<Long>, env: DataFetchingEnvironment): CompletableFuture<List<Order>> =
            future { orderService.findByIds(ids, env.toEntityRequestInfo()) }

    /**
     * Example method that uses the [WebClient] to make an HTTP request.
     */
    fun makeHttpRequest(url: String): CompletableFuture<String> {
        return future {
            logMessage("Making HTTP request to $url")
            WebClient
                    .create(url)
                    .get()
                    .accept(MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono<String>()
                    .awaitResult()
        }
    }

    fun customersRemoteDirect(url: String): CompletableFuture<List<Customer>> {
        return future {
            logMessage("Making HTTP request for customers to $url")
            WebClient
                    .create(url)
                    .get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono<List<Customer>>()
                    .awaitResult()
        }
    }

    fun auditRecords(): CompletableFuture<List<AuditRecord>> {
        return future {
            logMessage("Making HTTP request for all audit records")
            WebClient
                    .create("http://localhost:8081/audit/")
                    .get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono<List<AuditRecord>>()
                    .awaitResult()
        }
    }
}