package com.itheima.bos.web.action.base

import com.itheima.bos.domain.base.SubArea
import com.itheima.bos.service.base.SubAreaService
import com.itheima.bos.web.action.common.CommonAction
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
@Controller
@Namespace("/subArea")
@ParentPackage("struts-default")
@Scope("prototype")
class SubAreaAction : CommonAction<SubArea>() {
    private val SUCCESS = "success"
    private val NONE = "none"

    @Action(value = "save", results = arrayOf(Result(name = "success", location =
    "/pages/base/sub_area.html")))
    fun save(): String {
        service.save(model)
        return SUCCESS
    }

    @Action(value = "pageQuery")
    fun pageQuery(): String {
        println("pageQuery")
        val pr = PageRequest(page - 1, rows)
        val p: Page<SubArea> = service.pageQuery(pr)
        page2Json(p, Regex("subareas|couriers"))
        return NONE
    }

    @Autowired private lateinit var service: SubAreaService
}
