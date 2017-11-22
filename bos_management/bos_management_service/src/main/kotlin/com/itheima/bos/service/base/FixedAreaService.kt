package com.itheima.bos.service.base

import com.itheima.bos.domain.base.FixedArea
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
interface FixedAreaService{
    fun save(model: FixedArea)
    fun pageQuery(pr: PageRequest): Page<FixedArea>
    fun associationCourierToFixedArea(fixedAreaId: String, courierId: Int, takeTimeId: Int)
}