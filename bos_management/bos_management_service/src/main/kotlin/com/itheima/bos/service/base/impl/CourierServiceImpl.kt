package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.CourierDao
import com.itheima.bos.domain.base.Courier
import com.itheima.bos.service.base.CourierService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
@Service
@Transactional
open class CourierServiceImpl : CourierService {
    override fun findCouriersNotDelete(): List<Courier> = dao.findByDeltagIsNull()

    override fun pageQuery(spec: Specification<Courier>, rows: PageRequest): Page<Courier> =
            dao.findAll(spec, rows)

    override fun deleteBatch(ids: String) {
        val split = ids.split(",")
        for (id in split) {
            dao.invalid(id.toInt())
        }
    }

    override fun pageQuery(page: Int, rows: Int): Page<Courier> = dao.findAll(PageRequest(page, rows))

    override fun save(courier: Courier) {
        dao.save(courier)
    }

    @Autowired lateinit var dao: CourierDao
}