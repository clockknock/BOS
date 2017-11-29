package com.itheima.fore.web.action

import com.itheima.bos.crm.service.Customer
import com.itheima.bos.crm.service.CustomerService
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.commons.lang3.RandomStringUtils
import org.apache.struts2.ServletActionContext
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.convention.annotation.Result
import org.apache.struts2.interceptor.ServletResponseAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Controller
import javax.jms.Message
import javax.servlet.http.HttpServletResponse

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/customer")
@Scope("prototype")
class CustomerAction : ActionSupport(), ServletResponseAware, ModelDriven<Customer> {
    private val model = Customer()
    override fun getModel() = model

    private val NONE = "none"
    private val SUCCESS = "success"
    private val ERROR = "error"

    private lateinit var response: HttpServletResponse
    override fun setServletResponse(response: HttpServletResponse) {
        response.contentType = "text/html;charset=utf-8"
        this.response = response
    }

    @Action("validateCode")
    fun validateCode(): String {
        val validateCode = RandomStringUtils.randomNumeric(4)
        ServletActionContext.getRequest().session.setAttribute("validateCode", validateCode)
        response.writer.append(validateCode)
        return NONE
    }

    lateinit var checkcode: String

    @Action(value = "regist",
            results = arrayOf(
                    Result(name = "success", location = "/signup-success.html", type = "redirect"),
                    Result(name = "error", location = "/signup-fail.html", type = "redirect")))
    fun regist(): String {
        val sessionValiCode = ServletActionContext.getRequest().session.getAttribute("validateCode")
        return if (sessionValiCode != null && checkcode == sessionValiCode) {
            jmsTemplate.send("msg.sms", { session ->
                session.createTextMessage("${model.username}你好,您的帐号注册成功,请前往邮箱进行激活")!!
            })
            service.save(model)
            SUCCESS
        } else {
            ERROR
        }
    }

    @Action(value = "login", results = arrayOf(Result(name = "success", location = "/index.html",
            type = "redirect")))
    fun login(): String {
        service.login(model.telephone, model.password)

        return SUCCESS
    }

    @Suppress("SpringKotlinAutowiring")
    @Autowired lateinit var service: CustomerService
    @Autowired lateinit var jmsTemplate: JmsTemplate


}