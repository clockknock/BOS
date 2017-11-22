package com.itheima.bos.domain.base

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @description:子档案类，记录了档案分级后的子信息
 */
@Entity
@Table(name = "T_SUB_ARCHIVE")
class SubArchive {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键
    @Column(name = "C_SUB_ARCHIVE_NAME")
    var subArchiveName: String? = null // 子档名称
    @Column(name = "C_MNEMONIC_CODE")
    var mnemonicCode: String? = null // 助记码
    @Column(name = "C_REMARK")
    var remark: String? = null // 备注
    @Column(name = "C_MOTHBALLED")
    var mothballed: Char? = null // 封存标志
    @Column(name = "C_OPERATING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    var operatingTime: Date? = null// 操作时间
    @Column(name = "C_OPERATOR")
    var operator: String? = null // 操作员
    @Column(name = "C_OPERATING_COMPANY")
    var operatingCompany: String? = null // 操作单位

    @ManyToOne
    @JoinColumn(name = "C_ARCHIVE_ID")
    var archive: Archive? = null // 关联基本档案信息

}
