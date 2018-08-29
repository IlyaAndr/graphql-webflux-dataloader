package com.yg.auditservice

data class Customer(val id: Long,
                    var firstName: String,
                    var lastName: String,
                    var companyId: Long,
                    var pricingDetailsId: Long,
                    var outOfOfficeDelegate: Long? = null)