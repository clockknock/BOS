package com.itheima.bos.domain.takeDelivery

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import com.itheima.bos.domain.base.Area


/**
 * @description:运单实体类
 */
@Entity
@Table(name = "T_WAY_BILL")
class WayBill {

    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null
    @Column(name = "C_WAY_BILL_NUM", unique = true)
    var wayBillNum: String? = null // 运单编号
    @OneToOne
    @JoinColumn(name = "C_ORDER_ID")
    var order: Order? = null // 订单信息

    @Column(name = "C_SEND_NAME")
    var sendName: String? = null // 寄件人姓名
    @Column(name = "C_SEND_MOBILE")
    var sendMobile: String? = null// 寄件人电话
    @Column(name = "C_SEND_COMPANY")
    var sendCompany: String? = null// 寄件人公司
    @OneToOne
    @JoinColumn(name = "C_SEND_AREA_ID")
    var sendArea: Area? = null // 寄件人省市区信息
    @Column(name = "C_SEND_ADDRESS")
    var sendAddress: String? = null// 寄件人详细地址信息

    @Column(name = "C_REC_NAME")
    var recName: String? = null// 收件人姓名
    @Column(name = "C_REC_MOBILE")
    var recMobile: String? = null// 收件人电话
    @Column(name = "C_REC_COMPANY")
    var recCompany: String? = null// 收件人公司
    @OneToOne
    @JoinColumn(name = "C_REC_AREA_ID")
    var recArea: Area? = null // 收件人省市区信息
    @Column(name = "C_REC_ADDRESS")
    var recAddress: String? = null// 收件人详细地址信息

    @Column(name = "C_SEND_PRO_NUM")
    var sendProNum: String? = null // 快递产品类型编号：速运当日、速运次日、速运隔日
    @Column(name = "C_GOODS_TYPE")
    var goodsType: String? = null// 托寄物类型：文件、衣服 、食品、电子商品
    @Column(name = "C_PAY_TYPE_NUM")
    var payTypeNum: String? = null// 支付类型编号：寄付日结、寄付月结、到付
    @Column(name = "C_WEIGHT")
    var weight: Double? = null// 托寄物重量
    @Column(name = "C_REMARK")
    var remark: String? = null // 备注
    @Column(name = "C_NUM")
    var num: Int? = null // 原件数

    @Column(name = "C_ARRIVE_CITY")
    var arriveCity: String? = null // 到达地

    @Column(name = "C_FEEITEMNUM")
    var feeitemnum: Int? = null // 实际件数
    @Column(name = "C_ACTLWEIT")
    var actlweit: Double? = null // 实际重量
    @Column(name = "C_VOL")
    var vol: String? = null // 体积 输入格式为1*1*1*1，表示长*宽*高*数量
    @Column(name = "C_FLOADREQR")
    var floadreqr: String? = null // 配载要求 (比如录入1=无，2=禁航，4=禁航空铁路)

    @Column(name = "C_WAY_BILL_TYPE")
    var wayBillType: String? = null // 运单类型（类型包括：正常单据、异单、调单）
    /*
	 * 运单状态： 1 待发货、 2 派送中、3 已签收、4 异常
	 */
    @Column(name = "C_SIGN_STATUS")
    var signStatus: Int? = null // 签收状态

    /*
	 * 1、新增修改单据状态为“否” 2、作废时需将状态置为“是” 3、取消作废时需要将状态置为“否”
	 */
    @Column(name = "C_DELTAG")
    var delTag: String? = null // 作废标志

}
