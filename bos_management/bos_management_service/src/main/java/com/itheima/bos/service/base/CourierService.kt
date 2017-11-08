package com.itheima.bos.service.base

import com.itheima.bos.domain.base.Courier
import org.springframework.data.domain.Page

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
interface CourierService {
    fun save(courier: Courier)
    fun pageQuery(page: Int, rows: Int): Page<Courier>
}