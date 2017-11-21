package com.itheima.bos.crm.dao

import com.itheima.bos.crm.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
interface CustomerDao : JpaRepository<Customer,Int>