package com.itheima.fore.web.action

import com.itheima.bos.domain.Constants
import com.itheima.bos.domain.base.Area
import com.itheima.bos.domain.takeDelivery.Order
import com.itheima.bos.service.takedelivery.impl.OrderService
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/order")
@Scope("prototype")
class OrderAction : ActionSupport(), ModelDriven<Order> {
    private val model = Order()
    override fun getModel(): Order = model

    lateinit var sendAreaInfo: String
    lateinit var recAreaInfo: String

    @Action(value = "add")
    fun add(): String {
        if (!sendAreaInfo.isBlank()) {
            val split = sendAreaInfo.split("/")
            val sendArea = Area()
            sendArea.province = split[0]
            sendArea.city = split[1]
            sendArea.district = split[2]
            model.sendArea = sendArea
        }

        if (!recAreaInfo.isBlank()) {
            val split = recAreaInfo.split("/")
            val recArea = Area()
            recArea.province = split[0]
            recArea.city = split[1]
            recArea.district = split[2]
            model.recArea = recArea
        }

        orderService.save(model)
        return Constants.NONE
    }

    @Suppress("SpringKotlinAutowiring")
    @Autowired lateinit var  orderService: OrderService

}