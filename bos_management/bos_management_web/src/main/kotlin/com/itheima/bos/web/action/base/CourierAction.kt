package com.itheima.bos.web.action.base

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
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
import javax.servlet.http.HttpServletResponse

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
@Namespace("/courier")
@ParentPackage("struts-default")
class CourierAction : ActionSupport(), ModelDriven<Courier>, ServletResponseAware {
    private var page = 0
    private var rows = 0
    fun setPage(page: Int) {
        this.page = page
    }

    fun setRows(rows: Int) {
        this.rows = rows
    }

    private val SAVESUCCESS = "save_success"
    private val courier = Courier()
    override fun getModel(): Courier {
        return courier
    }

    lateinit var response: HttpServletResponse
    override fun setServletResponse(response: HttpServletResponse) {
        response.contentType = "text/html;charset=utf-8"
        this.response = response
    }

    @Action(value = "courierAction_save", results = arrayOf(Result(name = "save_success",
            location = "/pages/base/courier.html")))
    fun save(): String {
        service.save(courier)
        return SAVESUCCESS
    }

    @Action(value = "courierAction_pageQuery")
    fun pageQuery(): String {
        println("page:$page ,rows:$rows")
        val pageQuery = service.pageQuery(page - 1, rows)
        val gson = GsonBuilder().setExclusionStrategies(
                object :ExclusionStrategy{
                    override fun shouldSkipClass(clazz: Class<*>): Boolean {
                      return false
                    }

                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        val regex = Regex("fixedAreas|takeTime")

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