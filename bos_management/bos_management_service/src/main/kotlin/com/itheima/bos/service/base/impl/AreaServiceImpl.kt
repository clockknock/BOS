package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.AreaDao
import com.itheima.bos.domain.base.Area
import com.itheima.bos.service.base.AreaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
@Service
@Transactional
open class AreaServiceImpl : AreaService {
    override fun pageQuery(pageRequest: PageRequest): Page<Area> = dao.findAll(pageRequest)

    override fun save(areas: ArrayList<Area>) {
        areas.forEach{ dao.save(it) }
    }

    @Autowired lateinit var dao: AreaDao

}