package com.itheima.bos.dao.base

import com.itheima.bos.domain.base.Courier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
interface CourierDao: JpaRepository<Courier,Int>,JpaSpecificationExecutor<Courier> {
    fun findByDeltagIsNull(): List<Courier>
    @Modifying
    @Query("update Courier set deltag = 1 where id =?")
    fun invalid(id: Int)
}