package com.itheima.bos.web.action.base

import com.google.gson.Gson
import com.itheima.bos.domain.base.Standard
import com.itheima.bos.service.base.StandardService
import com.itheima.bos.web.action.common.CommonAction
import com.opensymphony.xwork2.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
@Controller
@Namespace("/standard")
@ParentPackage("struts-default")
@Scope("prototype")
class StandardAction : CommonAction<Standard>() {

    lateinit private var ids: String

    fun setIds(ids: String) {
        this.ids = ids
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
        page2Json(query, null)
//        val pageQuery = Gson().toJson(PageData(query.totalElements, query.content))
//        response.writer.append(pageQuery)

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