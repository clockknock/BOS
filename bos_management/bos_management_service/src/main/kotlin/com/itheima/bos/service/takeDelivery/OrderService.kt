package com.itheima.bos.service.takeDelivery

import com.itheima.bos.domain.takeDelivery.Order
import javax.jws.WebService

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
@WebService
interface OrderService{
    fun save(order: Order)
}