package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.StandardDao
import com.itheima.bos.service.base.StandardService
import com.itheima.bos.domain.base.Standard
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
@Service
@Transactional
open class StandardServiceImpl : StandardService {
    override fun findAll(): List<Standard> = dao.findAll()

    override fun deleteBatch(ids: String) {
        val idsList = ids.split(",")
        idsList.forEach { dao.deleteStandard(it.toInt()) }
    }

    override fun pageQuery(page: Int, rows: Int): Page<Standard> =
            dao.findAll(PageRequest(page, rows))

    override fun save(standard: Standard) {
        dao.save(standard)
    }


    @Autowired lateinit var dao: StandardDao

}