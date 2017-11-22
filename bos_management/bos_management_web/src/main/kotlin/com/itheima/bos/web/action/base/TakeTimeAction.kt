package com.itheima.bos.web.action.base

import com.itheima.bos.domain.base.TakeTime
import com.itheima.bos.service.base.TakeTimeService
import com.itheima.bos.web.action.common.CommonAction
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller

/**
 * Created by 钟未鸣 on 2017/11/22 .
 */
@Controller
@Namespace("/takeTime")
@ParentPackage("struts-default")
@Scope("prototype")
class TakeTimeAction: CommonAction<TakeTime>(){

    @Action(value = "listAjax")
    fun listAjax(): String{
        val list = service.findAll()
        list2Json(list)
        return NONE
    }

    @Autowired lateinit var service: TakeTimeService
}