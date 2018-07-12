/*
 * This file is generated by jOOQ.
 */
package com.yg.gqlwfdl.yg.db.public_;


import com.yg.gqlwfdl.yg.db.public_.tables.Company;
import com.yg.gqlwfdl.yg.db.public_.tables.Customer;
import com.yg.gqlwfdl.yg.db.public_.tables.records.CompanyRecord;
import com.yg.gqlwfdl.yg.db.public_.tables.records.CustomerRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>PUBLIC</code> schema.
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
    public static final Identity<CustomerRecord, Long> IDENTITY_CUSTOMER = Identities0.IDENTITY_CUSTOMER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CompanyRecord> CONSTRAINT_6 = UniqueKeys0.CONSTRAINT_6;
    public static final UniqueKey<CustomerRecord> CONSTRAINT_5 = UniqueKeys0.CONSTRAINT_5;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CompanyRecord, Long> IDENTITY_COMPANY = Internal.createIdentity(Company.COMPANY, Company.COMPANY.ID);
        public static Identity<CustomerRecord, Long> IDENTITY_CUSTOMER = Internal.createIdentity(Customer.CUSTOMER, Customer.CUSTOMER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<CompanyRecord> CONSTRAINT_6 = Internal.createUniqueKey(Company.COMPANY, "CONSTRAINT_6", Company.COMPANY.ID);
        public static final UniqueKey<CustomerRecord> CONSTRAINT_5 = Internal.createUniqueKey(Customer.CUSTOMER, "CONSTRAINT_5", Customer.CUSTOMER.ID);
    }
}
