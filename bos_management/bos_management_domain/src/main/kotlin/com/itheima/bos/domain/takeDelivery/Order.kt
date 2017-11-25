package com.itheima.bos.domain.takeDelivery

import com.itheima.bos.domain.base.Area
import com.itheima.bos.domain.base.Courier
import java.util.*
import javax.persistence.*


/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
/**
 * @description:订单实体类
 */
@Entity
@Table(name = "T_ORDER")
class Order {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null// 主键
    @Column(name = "C_ORDER_NUM")
    var orderNum: String? = null// 订单号

    @Column(name = "C_TELEPHONE")
    var telephone: String? = null // 来电号码

    @Column(name = "C_CUSTOMER_ID")
    var customer_id: Int? = null // 客户编号

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

    @Column(name = "C_SEND_MOBILE_MSG")
    var sendMobileMsg: String? = null // 给快递员捎话

    // 分单类型 1 自动分单 2 人工分单
    @Column(name = "C_ORDER_TYPE")
    var orderType: String? = null

    // 订单状态 1 待取件 2 运输中 3 已签收 4 异常
    @Column(name = "C_STATUS")
    var status: String? = null

    // 下单时间
    @Column(name = "C_ORDER_TIME")
    var orderTime: Date? = null

    // 运单
    @OneToOne(mappedBy = "order")
    var wayBill: WayBill? = null

    // 工单
    @OneToMany(mappedBy = "order")
    var workBills: Set<WorkBill> = HashSet<WorkBill>(0)

    @ManyToOne
    @JoinColumn(name = "C_COURIER_ID")
    var courier: Courier? = null

    override fun toString(): String {
        return ("Order [id=" + id + ", orderNum=" + orderNum + ", telephone="
                + telephone + ", customer_id=" + customer_id + ", sendName="
                + sendName + ", sendMobile=" + sendMobile + ", sendCompany="
                + sendCompany + ", sendArea=" + sendArea + ", sendAddress="
                + sendAddress + ", recName=" + recName + ", recMobile="
                + recMobile + ", recCompany=" + recCompany + ", recArea="
                + recArea + ", recAddress=" + recAddress + ", sendProNum="
                + sendProNum + ", goodsType=" + goodsType + ", payTypeNum="
                + payTypeNum + ", weight=" + weight + ", remark=" + remark
                + ", sendMobileMsg=" + sendMobileMsg + ", orderTime="
                + orderTime + "]")
    }

}
