package com.itheima.bos.domain.base

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @description:收派标准
 */
@Entity
@Table(name = "T_STANDARD")
class Standard {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键
    @Column(name = "C_NAME")
    var name: String? = null // 标准名称
    @Column(name = "C_MIN_WEIGHT")
    var minWeight: Int? = null // 最小重量
    @Column(name = "C_MAX_WEIGHT")
    var maxWeight: Int? = null // 最大重量
    @Column(name = "C_MIN_LENGTH")
    var minLength: Int? = null // 最小长度
    @Column(name = "C_MAX_LENGTH")
    var maxLength: Int? = null // 最大重量
    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    var operatingTime: Date? = null// 操作时间
    @Column(name = "C_OPERATOR")
    var operator: String? = null // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    var operatingCompany: String? = null // 操作单位

    override fun toString(): String {
        return ("Standard [id=" + id + ", name=" + name + ", minWeight="
                + minWeight + ", maxWeight=" + maxWeight + ", minLength="
                + minLength + ", maxLength=" + maxLength + ", operatingTime="
                + operatingTime + ", operator=" + operator
                + ", operatingCompany=" + operatingCompany + "]")
    }

}
