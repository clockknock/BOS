package com.itheima.bos.service.base


import com.itheima.bos.domain.base.Area
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
interface AreaService {
    fun save(areas: ArrayList<Area>)
    fun pageQuery(pageRequest: PageRequest): Page<Area>
    fun findAll(): List<Area>
    fun findByQ(q: String): List<Area>

}