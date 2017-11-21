package com.itheima.bos.web.action

import com.itheima.bos.domain.base.FixedArea
import com.itheima.bos.service.base.FixedAreaService
import com.itheima.bos.service.base.impl.FixedAreaServiceImpl
import com.itheima.bos.web.action.common.CommonAction
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
@Controller
@Namespace("/fixedArea")
@ParentPackage("struts-default")
@Scope("prototype")
class FixedAreaAction : CommonAction<FixedArea>(){
    private val SUCCESS="success"
    private val NONE="none"

    @Action(value = "save",results = arrayOf(Result(name = "success",location =
    "/pages/base/fixed_area.html")))
    fun save(): String{
        service.save(model)
        return SUCCESS
    }

    @Action(value="pageQuery")
    fun pageQuery(): String{
        val pr = PageRequest(page - 1, rows)

        val query = service.pageQuery(pr)
        page2Json(query,regex = Regex("subareas|couriers"))
        return NONE
    }

    @Autowired lateinit var service: FixedAreaService
}