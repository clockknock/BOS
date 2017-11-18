package com.itheima.bos.dao.base

import com.itheima.bos.domain.base.Standard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */

interface StandardDao : JpaRepository<Standard,Int>{
    fun findByName(name: String):Standard

    fun findByNameLike(name: String): List<Standard>

    fun findByNameIsNull():List<Standard>

    @Query("delete from Standard where id = ?")
    @Modifying //DML语句需要添加Modifying注解
    fun deleteStandard(id: Int)
}