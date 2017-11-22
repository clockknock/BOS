package com.itheima.bos.service.base

import com.itheima.bos.domain.base.TakeTime

/**
 * Created by 钟未鸣 on 2017/11/22 .
 */
interface  TakeTimeService{
    fun findAll(): List<TakeTime>
}