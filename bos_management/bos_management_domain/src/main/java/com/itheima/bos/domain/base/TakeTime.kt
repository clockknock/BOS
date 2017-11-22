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
 * @description:收派时间管理
 */
@Entity
@Table(name = "T_TAKE_TIME")
class TakeTime {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键
    @Column(name = "C_NAME")
    var name: String? = null // 收派时间名称
    @Column(name = "C_NORMAL_WORK_TIME")
    var normalWorkTime: String? = null // 平常上班时间
    @Column(name = "C_NORMAL_DUTY_TIME")
    var normalDutyTime: String? = null // 平常下班时间
    @Column(name = "C_SAT_WORK_TIME")
    var satWorkTime: String? = null // 周六上班时间
    @Column(name = "C_SAT_DUTY_TIME")
    var satDutyTime: String? = null // 周六下班时间
    @Column(name = "C_SUN_WORK_TIME")
    var sunWorkTime: String? = null // 周日上班时间
    @Column(name = "C_SUN_DUTY_TIME")
    var sunDutyTime: String? = null // 周日下班时间
    @Column(name = "C_STATUS")
    var status: String? = null // 状态
    @Column(name = "C_COMPANY")
    var company: String? = null // 所属公司

    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    var operatingTime: Date? = null// 操作时间
    @Column(name = "C_OPERATOR")
    var operator: String? = null // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    var operatingCompany: String? = null // 操作单位

}
