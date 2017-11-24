package com.itheima.bos.crm.service

import com.itheima.bos.crm.domain.Customer
import javax.jws.WebService

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
@WebService
interface  CustomerService{
    fun findAll(): List<Customer>
    fun findCustomersHasAssociation(id: String): List<Customer>
    fun findCustomersNotAssociation(): List<Customer>
    fun updateCustomers(ids: List<Int>,fixedAreaId: String)
    fun save(customer: Customer)
}