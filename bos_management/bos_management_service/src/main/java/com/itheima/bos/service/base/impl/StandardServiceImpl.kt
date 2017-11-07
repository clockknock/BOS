package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.StandardDao
import com.itheima.bos.service.base.StandardService
import com.itheima.bos.domain.base.Standard
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
@Service
@Transactional
open class StandardServiceImpl: StandardService {
    
    override fun save(model: Standard) {
        dao.save(model)
    }

    @Autowired lateinit var dao: StandardDao

}