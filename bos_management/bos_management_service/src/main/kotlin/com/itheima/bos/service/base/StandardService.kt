package com.itheima.bos.service.base

import com.itheima.bos.domain.base.Standard
import org.springframework.data.domain.Page

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
interface StandardService{
    fun save(standard: Standard)
    fun pageQuery(page: Int, rows: Int): Page<Standard>
    fun deleteBatch(ids: String)
    fun findAll(): List<Standard>

}