package com.itheima.bos.service.base

import com.itheima.bos.domain.base.Courier
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
interface CourierService {
    fun save(courier: Courier)
    fun pageQuery(page: Int, rows: Int): Page<Courier>
    fun deleteBatch(ids: String)
    fun pageQuery(spec: Specification<Courier>, rows: PageRequest): Page<Courier>
}