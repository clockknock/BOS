package com.itheima.bos.web.action.base

import com.itheima.bos.crm.service.CustomerService
import com.itheima.bos.domain.base.FixedArea
import com.itheima.bos.service.base.FixedAreaService
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
class FixedAreaAction : CommonAction<FixedArea>() {
    private val SUCCESS = "success"
    private val NONE = "none"

    @Action(value = "save", results = arrayOf(Result(name = "success", location =
    "/pages/base/fixed_area.html")))
    fun save(): String {
        service.save(model)
        return SUCCESS
    }

    @Action(value = "pageQuery")
    fun pageQuery(): String {
        val pr = PageRequest(page - 1, rows)

        val query = service.pageQuery(pr)
        page2Json(query, regex = Regex("subareas|couriers"))
        return NONE
    }

    @Action(value = "findCustomersNotAssociation")
    fun findCustomersNotAssociation(): String {
        val customers = customerService.findCustomersNotAssociation()
        list2Json(customers)
        return NONE
    }

    @Action(value = "findCustomersHasAssociation")
    fun findCustomersHasAssociation(): String {
        val customers = customerService.findCustomersHasAssociation(model.id)
        list2Json(customers)
        return NONE
    }

    @Suppress("MemberVisibilityCanPrivate")
    var customerIds: List<String>? = null

    @Action(value = "assignCustomers2FixedArea", results = arrayOf(Result(name = "success",
            location = "/pages/base/fixed_area.html")))
    fun assignCustomers2FixedArea(): String {
        //前端bug,传不了customerLids过来,只好拼接一趟
        val realIds = mutableListOf<Int>()

        if (customerIds != null && customerIds!!.isNotEmpty()) {
            customerIds!!.forEach {
                val substring = it.substring(0, it.indexOf("#"))
                println(substring)
                realIds.add(substring.toInt())
            }
        }
        customerService.updateCustomers(realIds, model.id)
        return SUCCESS
    }

    var takeTimeId: Int = 0
    var courierId: Int = 0
    @Action(value = "associationCourierToFixedArea", results = arrayOf(Result(name = "success",
            location = "/pages/base/fixed_area.html")))
    fun associationCourierToFixedArea(): String {
        service.associationCourierToFixedArea(model.id!!,courierId,takeTimeId)

        return SUCCESS
    }

    @Suppress("SpringKotlinAutowiring")//CustomerService放在依赖项目中,spring扫描不到
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var service: FixedAreaService
}