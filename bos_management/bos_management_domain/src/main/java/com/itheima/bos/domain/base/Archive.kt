package com.itheima.bos.domain.base

import java.util.Date
import java.util.HashSet

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @ description :档案类，记录所有的分类信息，在子档中
 */
@Entity
@Table(name = "T_ARCHIVE")
class Archive {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键
    @Column(name = "C_ARCHIVE_NUM", unique = true)
    var archiveNum: String? = null// 档案编号
    @Column(name = "C_ARCHIVE_NAME")
    var archiveName: String? = null // 档案名称
    @Column(name = "C_REMARK")
    var remark: String? = null // 备注
    @Column(name = "C_HASCHILD")
    var hasChild: Int? = null// 是否分级 0代表不分级 1代表分级
    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    var operatingTime: Date? = null// 操作时间
    @Column(name = "C_OPERATOR")
    var operator: String? = null // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    var operatingCompany: String? = null // 操作单位

    @OneToMany(mappedBy = "archive")
    var subArchives: Set<SubArchive> = HashSet() // 子档案

}
