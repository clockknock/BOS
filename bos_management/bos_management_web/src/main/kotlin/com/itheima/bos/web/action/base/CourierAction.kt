package com.itheima.bos.web.action.base

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.itheima.bos.domain.base.Courier
import com.itheima.bos.domain.base.PageData
import com.itheima.bos.service.base.CourierService
import com.itheima.bos.web.action.common.CommonAction
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.apache.struts2.interceptor.ServletResponseAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import java.util.ArrayList
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.servlet.http.HttpServletResponse

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
@Namespace("/courier")
@ParentPackage("struts-default")
@Scope("prototype")
class CourierAction :CommonAction<Courier>() {
    lateinit private var ids: String
    fun setIds(ids: String) {
        this.ids = ids
    }


    private val SAVESUCCESS = "save_success"
    private val DELETESUCCESS = "delete_success"
    private val model = Courier()
    override fun getModel(): Courier = model


    @Action(value = "save", results = arrayOf(Result(name = "save_success",
            location = "/pages/base/courier.html")))
    fun save(): String {
        println(model)
        service.save(model)
        return SAVESUCCESS
    }

    @Action(value = "deleteBatch", results = arrayOf(Result(name = "delete_success",
            location = "/pages/base/courier.html")))
    fun deleteBatch(): String {
        service.deleteBatch(ids)
        return DELETESUCCESS
    }

    @Action(value = "pageQuery")
    fun pageQuery(): String {
        val spec = CourierSpecification()

        val pageReq = PageRequest(page - 1, rows)
        val pageQuery = service.pageQuery(spec, pageReq)

        page2Json(pageQuery, Regex(pattern = "fixedAreas|takeTime"))
        return com.opensymphony.xwork2.Action.NONE
    }

    inner class CourierSpecification:Specification<Courier>{
        override fun toPredicate(root: Root<Courier>, query: CriteriaQuery<*>, cb:
        CriteriaBuilder): Predicate? {
            val standard = model.standard
            val courierNum = model.courierNum
            val company = model.company
            val type = model.type
            //动态根据页面提交的参数封装查询条件
            //用于收集查询条件对象
            val list = ArrayList<Predicate>()
            if (!company.isNullOrBlank()) {
                //需要条件一个过滤条件，根据公司模糊查询
                val p1 = cb.like(root.get<Any>("company").`as`(String::class.java), "%$company%")
                //where company like ?
                list.add(p1)
            }
            if (!courierNum.isNullOrBlank()) {
                //需要条件一个过滤条件，根据工号等值查询
                val p2 = cb.like(root.get<Any>("courierNum").`as`(String::class.java),
                        "%$courierNum%")//where company like ?
                list.add(p2)
            }
            if (!type.isNullOrBlank()) {
                //需要条件一个过滤条件，根据快递员类型等值查询
                val p3 = cb.like(root.get<Any>("type").`as`(String::class.java), "%$type%")//where
                // company like ?
                list.add(p3)
            }
            if (standard != null && !standard.name.isNullOrBlank()) {
                //需要条件一个过滤条件，根据收派标准名称等值查询
                val join = root.join<Any, Any>("standard")
                val p4 = cb.like(join.get<String>("name").`as`(String::class.java), "%${standard
                        .name}%")
                //where company like ?
                list.add(p4)
            }

            if (list.size == 0) {
                //没有过滤条件
                return null
            }

           return cb.and(*list.toTypedArray())
        }

    }


    @Autowired private lateinit var service: CourierService
}