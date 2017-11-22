package com.itheima.bos.domain.base

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * @description:车辆
 */
@Entity
@Table(name = "T_VEHICLE")
class Vehicle {

    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null
    @Column(name = "C_ROUTE_TYPE")
    var routeType: String? = null // 线路类型
    @Column(name = "C_ROUTE_NAME")
    var routeName: String? = null // 线路名称
    @Column(name = "C_SNIPPER")
    var shipper: String? = null // 承运商
    @Column(name = "C_DRIVER")
    var driver: String? = null // 司机
    @Column(name = "C_VEHICLE_NUM")
    var vehicleNum: String? = null // 车牌号
    @Column(name = "C_TELEPHONE")
    var telephone: String? = null // 电话
    @Column(name = "C_VEHICLE_TYPE")
    var vehicleType: String? = null// 车型
    @Column(name = "C_TON")
    var ton: Int? = null // 吨控
    @Column(name = "C_REMARK")
    var remark: String? = null// 备注

}
