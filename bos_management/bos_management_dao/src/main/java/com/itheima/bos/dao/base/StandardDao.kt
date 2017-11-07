package com.itheima.bos.dao.base

import com.itheima.bos.domain.base.Standard
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */

interface StandardDao : JpaRepository<Standard,Int>{
    fun findByName(name: String):Standard

    fun findByNameLike(name: String): List<Standard>

    fun findByNameIsNull():List<Standard>
}