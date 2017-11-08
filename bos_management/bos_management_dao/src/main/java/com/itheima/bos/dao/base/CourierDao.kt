package com.itheima.bos.dao.base

import com.itheima.bos.domain.base.Courier
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by 钟未鸣 on 2017/11/8 .
 */
interface CourierDao: JpaRepository<Courier,Int>