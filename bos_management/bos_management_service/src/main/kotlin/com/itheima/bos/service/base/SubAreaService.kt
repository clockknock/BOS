package com.itheima.bos.service.base

import com.itheima.bos.domain.base.SubArea
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
interface SubAreaService{
    fun save(subArea: SubArea)
    fun pageQuery(pr: PageRequest): Page<SubArea>
}