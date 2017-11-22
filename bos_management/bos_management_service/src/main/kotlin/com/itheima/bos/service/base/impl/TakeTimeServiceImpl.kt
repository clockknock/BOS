package com.itheima.bos.service.base.impl

import com.itheima.bos.dao.base.TakeTimeDao
import com.itheima.bos.domain.base.TakeTime
import com.itheima.bos.service.base.TakeTimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 钟未鸣 on 2017/11/22 .
 */
@Service
@Transactional
open class  TakeTimeServiceImpl : TakeTimeService{

    override fun findAll(): List<TakeTime> = dao.findAll()

    @Autowired lateinit var dao: TakeTimeDao

}