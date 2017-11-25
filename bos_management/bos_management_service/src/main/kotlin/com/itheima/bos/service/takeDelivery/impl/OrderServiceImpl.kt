package com.itheima.bos.service.takeDelivery.impl

import com.itheima.bos.crm.service.CustomerService
import com.itheima.bos.dao.base.AreaDao
import com.itheima.bos.dao.base.FixedAreaDao
import com.itheima.bos.dao.base.WorkBillDao
import com.itheima.bos.dao.takeDelivery.OrderDao
import com.itheima.bos.domain.Constants
import com.itheima.bos.domain.takeDelivery.Order
import com.itheima.bos.domain.takeDelivery.WorkBill
import com.itheima.bos.service.takeDelivery.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
@Service
@Transactional
open class OrderServiceImpl : OrderService {

    override fun save(order: Order) {
        val sendArea = order.sendArea
        if (sendArea != null) {
            val area = areaDao.findByProvinceAndCityAndDistrict(sendArea.province, sendArea.city, sendArea.district)
            order.sendArea = area
        }
        val recArea = order.recArea
        if (recArea != null) {
            val area = areaDao.findByProvinceAndCityAndDistrict(recArea.province, recArea.city, recArea.district)
            order.recArea = area
        }

        //1、保存订单信息到订单表
        order.orderTime = Date()
        order.orderNum = UUID.randomUUID().toString()
        dao.save(order)

        //2、基于CRM地址库完全匹配法实现自动分单--根据发件地址找到发件地址的快递员进行分配
        val sendAddress = order.sendAddress
        if (!sendAddress.isNullOrBlank()) {
            val fixedAreaId = customerService.findFixedAreaIdByAddress(order.sendAddress)
            val fixedArea = fixedAreaDao.findOne(fixedAreaId)
            val couriers = fixedArea.couriers
            val iterator = couriers.iterator()
            if (iterator.hasNext()) {
                //给order分配快递员
                order.orderType = Constants.ORDERTYPE_AUTO
                order.courier = iterator.next()
                //给快递员创建工单
                val workBill = WorkBill()
                workBill.attachbilltimes = 0
                workBill.buildtime = Date()
                workBill.courier = order.courier
                workBill.order = order
                workBill.pickstate =Constants.WORKBILLPICKSTATE_NEW
                workBill.remark =order.remark
                workBill.type =Constants.WORKBILLTYPE_NEW
                workBill.smsNumber = order.sendMobile

                workBillDao.save(workBill)
                println("工单信息：请到"+order.sendAddress+"取件，客户电话："+order.sendMobile)

            }

        }

        //3、基于分区关键字匹配法实现自动分单
        if (sendArea != null) {
            //获得指定区域中包含的所有分区
            val subareas = sendArea.subareas
            for (subArea in subareas) {
                val keyWords = subArea.keyWords//分区关键字
                val assistKeyWords = subArea.assistKeyWords//辅助关键字
                if (order.sendAddress!!.contains(keyWords!!) || order.sendAddress!!.contains
                (assistKeyWords!!)) {
                    val fixedArea = subArea.fixedArea
                    val couriers = fixedArea!!.couriers
                    //根据上下班时间进行匹配，匹配到正在值班的快递员
                    val iterator = couriers.iterator()
                    if (iterator.hasNext()) {
                        //查询到定区id了，可以完成自动分单
                        order.orderType=Constants.ORDERTYPE_AUTO
                        val courier = iterator.next()
                        order.courier = courier
                        //为快递员产生一个工单
                        val workBill = WorkBill()
                        workBill.attachbilltimes = 0
                        workBill.buildtime = Date()
                        workBill.courier = courier
                        workBill.order = order
                        workBill.pickstate = "新单"
                        workBill.remark = order.remark
                        workBill.smsNumber = "123"
                        workBill.type = "新"
                        workBillDao.save(workBill)//保存工单
                        //调用短信平台为快递员发送短信
                        System.out.println("工单信息：请到" + order.sendAddress + "取件，客户电话：" + order
                                .sendMobile)
                        return
                    }
                }
            }
        }


        //5、如果没有完成自动分单，转入人工调度
        order.orderType =Constants.ORDERTYPE_ARTIFICIAL
    }

    @Autowired lateinit var dao: OrderDao
    @Autowired lateinit var areaDao: AreaDao
    @Suppress("SpringKotlinAutowiring")
    @Autowired lateinit var customerService: CustomerService
    @Autowired lateinit var fixedAreaDao: FixedAreaDao
    @Autowired lateinit var workBillDao: WorkBillDao


}