package com.itheima.bos.crm.dao

import com.itheima.bos.crm.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
interface CustomerDao : JpaRepository<Customer,Int> {
    fun findByFixedAreaIdIsNull(): List<Customer>
    fun findByFixedAreaId(id: String): List<Customer>
}