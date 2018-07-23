package com.yg.gqlwfdl.dataloaders

import com.yg.gqlwfdl.RequestContext
import com.yg.gqlwfdl.services.*
import org.dataloader.DataLoaderRegistry
import org.springframework.stereotype.Component

/**
 * Class responsible for creating all the [EntityDataLoader]s in the system.
 */
@Component
class DataLoaderFactory(private val customerService: CustomerService,
                        private val companyService: CompanyService,
                        private val companyPartnershipService: CompanyPartnershipService,
                        private val pricingDetailsService: PricingDetailsService) {

    /**
     * Creates a new instance of every data loader (for all the items in [DataLoaderType]) and registers it with the
     * passed in [DataLoaderRegistry].
     *
     * @param registry The registry with which to register each of the data loaders.
     * @param requestContext The object providing access to the current request context.
     */
    fun createAllAndRegister(registry: DataLoaderRegistry, requestContext: RequestContext) {
        DataLoaderType.values().forEach {
            // Use a "when" to ensure that every type is included: compiler will fail if not every entry in the enum
            // is handled.
            val dataLoader = when (it) {
                DataLoaderType.COMPANY ->
                    EntityDataLoader<Long, Company> { companyService.findByIds(it) }

                DataLoaderType.CUSTOMER ->
                    EntityDataLoader<Long, Customer> { customerService.findByIds(it) }

                DataLoaderType.COMPANY_PARTNERSHIP ->
                    EntityDataLoader<Long, CompanyPartnership> { companyPartnershipService.findByIds(it) }

                DataLoaderType.PRICING_DETAILS ->
                    EntityDataLoader<Long, PricingDetails> { pricingDetailsService.findByIds(it) }
            }

            registry.register(it.registryKey, dataLoader)
        }
    }
}

// Below are the extension methods on RequestContext to provide easy access to all the data loaders created above.

/**
 * Gets the data loader for caching/loading customers ([Customer] objects).
 */
val RequestContext.customerDataLoader
    get() = this.dataLoader<Long, Customer>(DataLoaderType.CUSTOMER)

/**
 * Gets the data loader for caching/loading companies ([Company] objects).
 */
val RequestContext.companyDataLoader
    get() = this.dataLoader<Long, Company>(DataLoaderType.COMPANY)

/**
 * Gets the data loader for caching/loading company partnerships ([CompanyPartnership] objects).
 */
@Suppress("unused")
val RequestContext.companyPartnershipDataLoader
    get() = this.dataLoader<Long, CompanyPartnership>(DataLoaderType.COMPANY)

/**
 * Gets the data loader for caching/loading companies ([Company] objects).
 */
val RequestContext.pricingDetailsDataLoader
    get() = this.dataLoader<Long, PricingDetails>(DataLoaderType.PRICING_DETAILS)