package com.itheima.bos.web.action.base

import com.google.gson.Gson
import com.itheima.bos.domain.base.PageData
import com.itheima.bos.domain.base.Standard
import com.itheima.bos.service.base.StandardService
import com.opensymphony.xwork2.Action
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.apache.struts2.interceptor.ServletResponseAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import javax.servlet.http.HttpServletResponse

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
@Namespace("/standard")
@ParentPackage("struts-default")
@Scope("prototype")
class StandardAction : ActionSupport(), ModelDriven<Standard>, ServletResponseAware {
    lateinit var response: HttpServletResponse
    override fun setServletResponse(response: HttpServletResponse) {
        response.contentType = "text/html;charset=utf-8"
        this.response = response
    }

    private var page: Int = 0//当前页码
    private var rows: Int = 0//每页显示的记录数
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

    private val model = Standard()
    override fun getModel(): Standard = model


    /**
     * 增加标准
     */
    @org.apache.struts2.convention.annotation.Action(value = "save", results =
    arrayOf(Result(name = "success", location = "/pages/base/standard.html")))
    fun save(): String {
        service.save(model)
        return Action.SUCCESS
    }

    /**
     * 分页查找
     */
    @org.apache.struts2.convention.annotation.Action(value = "pageQuery")
    fun pageQuery(): String {
        val query = service.pageQuery(page - 1, rows)
        val pageQuery = Gson().toJson(PageData(query.totalElements, query.content))
        response.writer.append(pageQuery)

        return Action.NONE
    }

    /**
     * 批量删除
     */
    @org.apache.struts2.convention.annotation.Action(value = "deleteBatch", results =
    arrayOf(Result(name = "success", location = "/pages/base/standard.html")))
    fun deleteBatch(): String {
        service.deleteBatch(ids)
        return Action.SUCCESS
    }

    /**
     * 分页查找
     */
    @org.apache.struts2.convention.annotation.Action(value = "findAll")
    fun findAll(): String {
        val list: List<Standard> = service.findAll()

        response.writer.append(Gson().toJson(list))

        return Action.NONE
    }


    @Autowired private lateinit var service: StandardService

}