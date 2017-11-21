package com.itheima.bos.crm.domain

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @description:客户信息表
 */
@Entity
@Table(name = "T_CUSTOMER")
class Customer() {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键id
    @Column(name = "C_USERNAME")
    var username: String? = null // 用户名
    @Column(name = "C_PASSWORD")
    var password: String? = null // 密码
    @Column(name = "C_TYPE")
    var type: Int? = null // 类型
    @Column(name = "C_BRITHDAY")
    @Temporal(TemporalType.DATE)
    var birthday: Date? = null // 生日
    @Column(name = "C_SEX")
    var sex: Int? = null // 性别
    @Column(name = "C_TELEPHONE")
    var telephone: String? = null // 手机
    @Column(name = "C_COMPANY")
    var company: String? = null // 公司
    @Column(name = "C_DEPARTMENT")
    var department: String? = null // 部门
    @Column(name = "C_POSITION")
    var position: String? = null // 职位
    @Column(name = "C_ADDRESS")
    var address: String? = null // 地址
    @Column(name = "C_MOBILEPHONE")
    var mobilePhone: String? = null // 座机
    @Column(name = "C_EMAIL")
    var email: String? = null // 邮箱
    @Column(name = "C_Fixed_AREA_ID")
    var fixedAreaId: String? = null // 定区编码
     constructor(id: Int,username: String):this(){
         this.id = id
         this.username = username

    }

    override fun toString(): String =
            "Customer(id=$id, username=$username, password=$password, type=$type, birthday=$birthday, sex=$sex, telephone=$telephone, company=$company, department=$department, position=$position, address=$address, mobilePhone=$mobilePhone, email=$email, fixedAreaId=$fixedAreaId)"

}