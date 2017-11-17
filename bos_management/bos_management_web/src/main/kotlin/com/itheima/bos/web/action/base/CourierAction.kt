package com.itheima.bos.web.action.base

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.itheima.bos.domain.base.Courier
import com.itheima.bos.domain.base.PageData
import com.itheima.bos.service.base.CourierService
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.apache.struts2.interceptor.ServletResponseAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import javax.servlet.http.HttpServletResponse

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
@Namespace("/courier")
@ParentPackage("struts-default")
@Scope("prototype")
class CourierAction : ActionSupport(), ModelDriven<Courier>, ServletResponseAware {
    private var page = 0
    private var rows = 0
    lateinit private var ids: String
    fun setIds(ids: String) {
        this.ids = ids
    }

    fun setPage(page: Int) {
        this.page = page
    }

    fun setRows(rows: Int) {
        this.rows = rows
    }

    private val SAVESUCCESS = "save_success"
    private val DELETESUCCESS = "delete_success"
    private val model = Courier()
    override fun getModel(): Courier = model

    lateinit var response: HttpServletResponse
    override fun setServletResponse(response: HttpServletResponse) {
        response.contentType = "text/html;charset=utf-8"
        this.response = response
    }

    @Action(value = "save", results = arrayOf(Result(name = "save_success",
            location = "/pages/base/courier.html")))
    fun save(): String {
        println(model)
        service.save(model)
        return SAVESUCCESS
    }
    @Action(value = "deleteBatch",results = arrayOf(Result(name = "delete_success",
            location = "/pages/base/courier.html")))
    fun deleteBatch(): String{
        println("courierDelete:$ids")
        service.deleteBatch(ids)
        return DELETESUCCESS
    }

    @Action(value = "pageQuery")
    fun pageQuery(): String {
        println(model)
        val pageQuery = service.pageQuery(page - 1, rows)
        val gson = GsonBuilder().setExclusionStrategies(
                object : ExclusionStrategy {
                    override fun shouldSkipClass(clazz: Class<*>): Boolean = false

                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        val regex = Regex(pattern = "fixedAreas|takeTime")

                        return regex.matches(f.name)
                    }
                }
        ).create()
        val json = gson.toJson(PageData(pageQuery.totalElements, pageQuery.content))
        response.writer.append(json)

        return com.opensymphony.xwork2.Action.NONE
    }

    @Autowired private lateinit var service: CourierService
}