package com.itheima.bos.dao.takeDelivery

import com.itheima.bos.domain.takeDelivery.Order
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
interface OrderDao : JpaRepository<Order,Int>