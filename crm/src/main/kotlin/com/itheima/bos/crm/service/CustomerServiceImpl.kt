package com.itheima.bos.crm.service

import com.itheima.bos.crm.dao.CustomerDao
import com.itheima.bos.crm.domain.Customer
import com.itheima.bos.crm.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
@Service("customerServiceImpl")
@Transactional
open class CustomerServiceImpl : CustomerService {
    override fun updateCustomers(ids: List<Int>, fixedAreaId: String) {
        dao.setFixedAreaIdIsNull(fixedAreaId)
        for (id in ids) {
            dao.assignCustomers2FixedArea(fixedAreaId,id)
        }
    }

    override fun findCustomersHasAssociation(id: String): List<Customer> = dao.findByFixedAreaId(id)

    override fun findCustomersNotAssociation(): List<Customer> = dao.findByFixedAreaIdIsNull()

    override fun findAll(): List<Customer> = dao.findAll()

    @Autowired lateinit var dao: CustomerDao

}