package com.itheima.bos.domain.base

import com.alibaba.fastjson.annotation.JSONField

import java.util.HashSet

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * @description:快递员
 */
@Entity
@Table(name = "T_COURIER")
class Courier {

    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键
    @Column(name = "C_COURIER_NUM", unique = true)
    var courierNum: String? = null // 快递员工号
    @Column(name = "C_NAME")
    var name: String? = null // 快递员姓名
    @Column(name = "C_TELEPHONE")
    var telephone: String? = null // 快递员联系电话
    @Column(name = "C_PDA")
    var pda: String? = null // PDA号
    @Column(name = "C_DELTAG")
    var deltag: Char? = null // 作废标志 1 为标记作废
    @Column(name = "C_CHECK_PWD")
    var checkPwd: String? = null // 查台密码
    @Column(name = "C_TYPE")
    var type: String? = null // 取件员类型
    @Column(name = "C_COMPANY")
    var company: String? = null // 单位
    @Column(name = "C_VEHICLE_TYPE")
    var vehicleType: String? = null // 车辆类型
    @Column(name = "C_VEHICLE_NUM")
    var vehicleNum: String? = null // 车牌号

    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "C_STANDARD_ID")
    var standard: Standard? = null

    @ManyToOne
    @JSONField(serialize = false)
    @JoinColumn(name = "C_TAKETIME_ID")
    var takeTime: TakeTime? = null

    @ManyToMany(mappedBy = "couriers")
    @JSONField(serialize = false)
    var fixedAreas: Set<FixedArea> = HashSet()

    constructor() {}

    constructor(courierNum: String, type: String, company: String, standard: Standard) {
        this.courierNum = courierNum
        this.type = type
        this.company = company
        this.standard = standard
    }

    override fun toString(): String {
        return "Courier{" +
                "id=" + id +
                ", courierNum='" + courierNum + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pda='" + pda + '\'' +
                ", deltag=" + deltag +
                ", checkPwd='" + checkPwd + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleNum='" + vehicleNum + '\'' +
                ", standard=" + standard +
                ", takeTime=" + takeTime +
                '}'
    }
}
