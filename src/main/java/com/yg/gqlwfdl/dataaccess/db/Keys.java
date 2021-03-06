/*
 * This file is generated by jOOQ.
 */
package com.yg.gqlwfdl.dataaccess.db;


import com.yg.gqlwfdl.dataaccess.db.tables.Company;
import com.yg.gqlwfdl.dataaccess.db.tables.CompanyPartnership;
import com.yg.gqlwfdl.dataaccess.db.tables.Customer;
import com.yg.gqlwfdl.dataaccess.db.tables.DiscountRate;
import com.yg.gqlwfdl.dataaccess.db.tables.Order;
import com.yg.gqlwfdl.dataaccess.db.tables.OrderLine;
import com.yg.gqlwfdl.dataaccess.db.tables.PaymentMethod;
import com.yg.gqlwfdl.dataaccess.db.tables.PricingDetails;
import com.yg.gqlwfdl.dataaccess.db.tables.Product;
import com.yg.gqlwfdl.dataaccess.db.tables.VatRate;
import com.yg.gqlwfdl.dataaccess.db.tables.records.CompanyPartnershipRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.CompanyRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.CustomerRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.DiscountRateRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.OrderLineRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.OrderRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.PaymentMethodRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.PricingDetailsRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.ProductRecord;
import com.yg.gqlwfdl.dataaccess.db.tables.records.VatRateRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CompanyRecord, Long> IDENTITY_COMPANY = Identities0.IDENTITY_COMPANY;
    public static final Identity<CompanyPartnershipRecord, Long> IDENTITY_COMPANY_PARTNERSHIP = Identities0.IDENTITY_COMPANY_PARTNERSHIP;
    public static final Identity<CustomerRecord, Long> IDENTITY_CUSTOMER = Identities0.IDENTITY_CUSTOMER;
    public static final Identity<DiscountRateRecord, Long> IDENTITY_DISCOUNT_RATE = Identities0.IDENTITY_DISCOUNT_RATE;
    public static final Identity<OrderRecord, Long> IDENTITY_ORDER = Identities0.IDENTITY_ORDER;
    public static final Identity<OrderLineRecord, Long> IDENTITY_ORDER_LINE = Identities0.IDENTITY_ORDER_LINE;
    public static final Identity<PaymentMethodRecord, Long> IDENTITY_PAYMENT_METHOD = Identities0.IDENTITY_PAYMENT_METHOD;
    public static final Identity<PricingDetailsRecord, Long> IDENTITY_PRICING_DETAILS = Identities0.IDENTITY_PRICING_DETAILS;
    public static final Identity<ProductRecord, Long> IDENTITY_PRODUCT = Identities0.IDENTITY_PRODUCT;
    public static final Identity<VatRateRecord, Long> IDENTITY_VAT_RATE = Identities0.IDENTITY_VAT_RATE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CompanyRecord> COMPANY_PKEY = UniqueKeys0.COMPANY_PKEY;
    public static final UniqueKey<CompanyPartnershipRecord> COMPANY_PARTNERSHIP_PKEY = UniqueKeys0.COMPANY_PARTNERSHIP_PKEY;
    public static final UniqueKey<CustomerRecord> CUSTOMER_PKEY = UniqueKeys0.CUSTOMER_PKEY;
    public static final UniqueKey<DiscountRateRecord> DISCOUNT_RATE_PKEY = UniqueKeys0.DISCOUNT_RATE_PKEY;
    public static final UniqueKey<OrderRecord> ORDER_PKEY = UniqueKeys0.ORDER_PKEY;
    public static final UniqueKey<OrderLineRecord> ORDER_LINE_PKEY = UniqueKeys0.ORDER_LINE_PKEY;
    public static final UniqueKey<PaymentMethodRecord> PAYMENT_METHOD_PKEY = UniqueKeys0.PAYMENT_METHOD_PKEY;
    public static final UniqueKey<PricingDetailsRecord> PRICING_DETAILS_PKEY = UniqueKeys0.PRICING_DETAILS_PKEY;
    public static final UniqueKey<ProductRecord> PRODUCT_PKEY = UniqueKeys0.PRODUCT_PKEY;
    public static final UniqueKey<VatRateRecord> VAT_RATE_PKEY = UniqueKeys0.VAT_RATE_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CompanyRecord, Long> IDENTITY_COMPANY = Internal.createIdentity(Company.COMPANY, Company.COMPANY.ID);
        public static Identity<CompanyPartnershipRecord, Long> IDENTITY_COMPANY_PARTNERSHIP = Internal.createIdentity(CompanyPartnership.COMPANY_PARTNERSHIP, CompanyPartnership.COMPANY_PARTNERSHIP.ID);
        public static Identity<CustomerRecord, Long> IDENTITY_CUSTOMER = Internal.createIdentity(Customer.CUSTOMER, Customer.CUSTOMER.ID);
        public static Identity<DiscountRateRecord, Long> IDENTITY_DISCOUNT_RATE = Internal.createIdentity(DiscountRate.DISCOUNT_RATE, DiscountRate.DISCOUNT_RATE.ID);
        public static Identity<OrderRecord, Long> IDENTITY_ORDER = Internal.createIdentity(Order.ORDER, Order.ORDER.ID);
        public static Identity<OrderLineRecord, Long> IDENTITY_ORDER_LINE = Internal.createIdentity(OrderLine.ORDER_LINE, OrderLine.ORDER_LINE.ID);
        public static Identity<PaymentMethodRecord, Long> IDENTITY_PAYMENT_METHOD = Internal.createIdentity(PaymentMethod.PAYMENT_METHOD, PaymentMethod.PAYMENT_METHOD.ID);
        public static Identity<PricingDetailsRecord, Long> IDENTITY_PRICING_DETAILS = Internal.createIdentity(PricingDetails.PRICING_DETAILS, PricingDetails.PRICING_DETAILS.ID);
        public static Identity<ProductRecord, Long> IDENTITY_PRODUCT = Internal.createIdentity(Product.PRODUCT, Product.PRODUCT.ID);
        public static Identity<VatRateRecord, Long> IDENTITY_VAT_RATE = Internal.createIdentity(VatRate.VAT_RATE, VatRate.VAT_RATE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<CompanyRecord> COMPANY_PKEY = Internal.createUniqueKey(Company.COMPANY, "company_pkey", Company.COMPANY.ID);
        public static final UniqueKey<CompanyPartnershipRecord> COMPANY_PARTNERSHIP_PKEY = Internal.createUniqueKey(CompanyPartnership.COMPANY_PARTNERSHIP, "company_partnership_pkey", CompanyPartnership.COMPANY_PARTNERSHIP.ID);
        public static final UniqueKey<CustomerRecord> CUSTOMER_PKEY = Internal.createUniqueKey(Customer.CUSTOMER, "customer_pkey", Customer.CUSTOMER.ID);
        public static final UniqueKey<DiscountRateRecord> DISCOUNT_RATE_PKEY = Internal.createUniqueKey(DiscountRate.DISCOUNT_RATE, "discount_rate_pkey", DiscountRate.DISCOUNT_RATE.ID);
        public static final UniqueKey<OrderRecord> ORDER_PKEY = Internal.createUniqueKey(Order.ORDER, "order_pkey", Order.ORDER.ID);
        public static final UniqueKey<OrderLineRecord> ORDER_LINE_PKEY = Internal.createUniqueKey(OrderLine.ORDER_LINE, "order_line_pkey", OrderLine.ORDER_LINE.ID);
        public static final UniqueKey<PaymentMethodRecord> PAYMENT_METHOD_PKEY = Internal.createUniqueKey(PaymentMethod.PAYMENT_METHOD, "payment_method_pkey", PaymentMethod.PAYMENT_METHOD.ID);
        public static final UniqueKey<PricingDetailsRecord> PRICING_DETAILS_PKEY = Internal.createUniqueKey(PricingDetails.PRICING_DETAILS, "pricing_details_pkey", PricingDetails.PRICING_DETAILS.ID);
        public static final UniqueKey<ProductRecord> PRODUCT_PKEY = Internal.createUniqueKey(Product.PRODUCT, "product_pkey", Product.PRODUCT.ID);
        public static final UniqueKey<VatRateRecord> VAT_RATE_PKEY = Internal.createUniqueKey(VatRate.VAT_RATE, "vat_rate_pkey", VatRate.VAT_RATE.ID);
    }
}
