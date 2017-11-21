package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.SubAreaDao
import com.itheima.bos.domain.base.SubArea
import com.itheima.bos.service.base.SubAreaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
@Service
@Transactional
open class SubAreaServiceImpl : SubAreaService {
    override fun pageQuery(pr: PageRequest): Page<SubArea> = dao.findAll(pr)

    override fun save(subArea: SubArea) {

        subArea.id = UUID.randomUUID().toString().replace("-","")
        dao.save(subArea)
    }

    @Autowired lateinit var dao: SubAreaDao

}