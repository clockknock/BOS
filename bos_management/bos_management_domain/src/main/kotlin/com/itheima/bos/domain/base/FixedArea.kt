package com.itheima.bos.domain.base

import java.util.Date
import java.util.HashSet

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @description:定区
 */
@Entity
@Table(name = "T_FIXED_AREA")
class FixedArea {

    @Id
    @Column(name = "C_ID")
    var id: String? = null // 主键
    @Column(name = "C_FIXED_AREA_NAME", unique = true)
    var fixedAreaName: String? = null // 定区名称
    @Column(name = "C_FIXED_AREA_LEADER", unique = true)
    var fixedAreaLeader: String? = null// 定区负责人
    @Column(name = "C_TELEPHONE")
    var telephone: String? = null// 联系电话
    @Column(name = "C_COMPANY")
    var company: String? = null // 所属单位

    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    var operatingTime: Date? = null// 操作时间
    @Column(name = "C_OPERATOR")
    var operator: String? = null // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    var operatingCompany: String? = null // 操作单位

    @OneToMany(mappedBy = "fixedArea")
    var subareas: Set<SubArea> = HashSet(0)

    @ManyToMany
    @JoinTable(name = "T_FIXEDAREA_COURIER",
            joinColumns = arrayOf(JoinColumn(name = "C_FIXED_AREA_ID", referencedColumnName = "C_ID"))
            , inverseJoinColumns = arrayOf(JoinColumn(name = "C_COURIER_ID", referencedColumnName = "C_ID")))
    var couriers: Set<Courier> = HashSet(0)

}
