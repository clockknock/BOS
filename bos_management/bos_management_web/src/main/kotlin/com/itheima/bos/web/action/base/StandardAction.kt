package com.itheima.bos.web.action.base

import com.itheima.bos.domain.base.Standard
import com.itheima.bos.service.base.StandardService
import com.itheima.bos.service.base.impl.StandardServiceImpl
import com.opensymphony.xwork2.Action
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
@Namespace("/standard")
@ParentPackage("struts-default")
class StandardAction : ActionSupport(),ModelDriven<Standard>{
    private val standard = Standard()
    override fun getModel(): Standard {
        return standard
    }

    @org.apache.struts2.convention.annotation.Action(value="standardAction_save",results =
    arrayOf(Result(name = "success",location = "/pages/base/standard.html")))
    fun save(): String{
        service.save(standard)
        return Action.SUCCESS
    }

    @Autowired lateinit var service: StandardService

}