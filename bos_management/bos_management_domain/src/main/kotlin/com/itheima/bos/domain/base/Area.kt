package com.itheima.bos.domain.base

import com.alibaba.fastjson.annotation.JSONField
import java.util.HashSet
import javax.persistence.*
import kotlin.reflect.KProperty

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */

/**
 * @description:地域信息实体类，主要包含 省市区(县)
 */
@Entity
@Table(name = "T_AREA")
class Area {

    @Id
    @Column(name = "C_ID")
    var id: String? = null
    @Column(name = "C_PROVINCE")
    var province: String? = null // 省
    @Column(name = "C_CITY")
    var city: String? = null // 城市
    @Column(name = "C_DISTRICT")
    var district: String? = null // 区域
    @Column(name = "C_POSTCODE")
    var postcode: String? = null // 邮编
    @Column(name = "C_CITYCODE")
    var citycode: String? = null // 城市编码
    @Column(name = "C_SHORTCODE")
    var shortcode: String? = null // 简码


    @JSONField(serialize = false)
    @OneToMany(mappedBy = "area")
    var subareas: Set<SubArea> = HashSet()

    fun getName() = province + city + district
    override fun toString(): String {
        return "Area(id=$id, province=$province, city=$city, district=$district, postcode=$postcode, citycode=$citycode, shortcode=$shortcode)"
    }


}


