package com.itheima.bos.service.base

import com.itheima.bos.domain.base.Area
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
interface AreaService{
    fun save(areas: ArrayList<Area>)
    fun pageQuery(pageRequest: PageRequest): Page<Area>

}