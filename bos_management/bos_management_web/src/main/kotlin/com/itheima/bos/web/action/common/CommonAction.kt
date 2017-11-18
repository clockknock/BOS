package com.itheima.bos.web.action.common

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.itheima.bos.domain.base.PageData
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.struts2.interceptor.ServletResponseAware
import org.springframework.data.domain.Page
import java.lang.reflect.ParameterizedType
import java.util.*
import javax.servlet.http.HttpServletResponse


/**
 * Created by 钟未鸣 on 2017/11/17 .
 */
open class CommonAction<T> : ActionSupport(), ModelDriven<T>, ServletResponseAware {

    private  var model: T? = null// 声明模型对象
    override fun getModel(): T? = model

    // 在运行期动态获得泛型的具体类型，并通过反射创建model对象
    init {
        // 获得父类（CommonAction）对应的类型
        val genericSuperclass = this.javaClass.genericSuperclass as ParameterizedType
        // 获得父类上声明的泛型类型数组
        val actualTypeArguments = genericSuperclass.actualTypeArguments
        // 获得实体类型
        val entityClass = actualTypeArguments[0] as Class<*>
        // 通过反射创建model对象
        @Suppress("UNCHECKED_CAST")
            model = entityClass.newInstance() as T
    }

    // 使用属性驱动接收页面提交的分页参数
    open var page: Int = 0// 当前页码
    open var rows: Int = 0// 每页显示的记录数

    private lateinit var response: HttpServletResponse
    override fun setServletResponse(response: HttpServletResponse) {
        response.contentType="text/html;charset=utf-8"
        this.response = response
    }

    /**
     * 将分页数据转为json响应到页面
     * @param regex: 转json时需要过滤的field
     */
    fun page2Json(page: Page<*>,regex: Regex?) {
        val total = page.totalElements
        val rows = page.content
        val map = HashMap<String, Any>()
        map.put("total", total)
        map.put("rows", rows)
        val gson = GsonBuilder().setExclusionStrategies(
                object : ExclusionStrategy {
                    override fun shouldSkipClass(clazz: Class<*>): Boolean = false

                    override fun shouldSkipField(f: FieldAttributes): Boolean {

                        if (regex == null) {
                            return false
                        }
                        return regex.matches(f.name)
                    }
                }
        ).create()
        val json = gson.toJson(PageData(page.totalElements, page.content))
        response.writer.append(json)
    }
}

