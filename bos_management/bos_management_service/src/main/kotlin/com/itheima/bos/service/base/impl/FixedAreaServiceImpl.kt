package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.FixedAreaDao
import com.itheima.bos.domain.base.FixedArea
import com.itheima.bos.service.base.FixedAreaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
@Service
@Transactional
open class FixedAreaServiceImpl : FixedAreaService{
    override fun pageQuery(pr: PageRequest): Page<FixedArea> {
        return dao.findAll(pr)
    }

    override fun save(model: FixedArea) {
        dao.save(model)
    }

    @Autowired lateinit var dao: FixedAreaDao

}