/*
 * This file is generated by jOOQ.
 */
package com.yg.gqlwfdl.yg.db.public_;


import com.yg.gqlwfdl.yg.db.public_.tables.Company;
import com.yg.gqlwfdl.yg.db.public_.tables.Customer;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index PRIMARY_KEY_6 = Indexes0.PRIMARY_KEY_6;
    public static final Index PRIMARY_KEY_5 = Indexes0.PRIMARY_KEY_5;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index PRIMARY_KEY_6 = Internal.createIndex("PRIMARY_KEY_6", Company.COMPANY, new OrderField[] { Company.COMPANY.ID }, true);
        public static Index PRIMARY_KEY_5 = Internal.createIndex("PRIMARY_KEY_5", Customer.CUSTOMER, new OrderField[] { Customer.CUSTOMER.ID }, true);
    }
}
