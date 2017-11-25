package com.itheima.bos.domain.takeDelivery

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

import com.itheima.bos.domain.base.Courier


/**
 * @description:工单
 */
@Entity
@Table(name = "T_WORK_BILL")
class WorkBill {
    @Id
    @GeneratedValue
    @Column(name = "C_ID")
    var id: Int? = null // 主键
    @Column(name = "C_TYPE")
    var type: String? = null // 工单类型 新,追,销
    /*
	 * 新单:没有确认货物状态的 已通知:自动下单下发短信 已确认:接到短信,回复收信确认信息 已取件:已经取件成功,发回确认信息 生成工作单
	 * 已取消:销单
	 *
	 */
    @Column(name = "C_PICKSTATE")
    var pickstate: String? = null // 取件状态
    @Column(name = "C_BUILDTIME")
    var buildtime: Date? = null // 工单生成时间
    @Column(name = "C_ATTACHBILLTIMES")
    var attachbilltimes: Int? = null // 追单次数
    @Column(name = "C_REMARK")
    var remark: String? = null // 订单备注
    @Column(name = "C_SMSNUMBER")
    var smsNumber: String? = null // 短信序号

    @OneToOne
    @JoinColumn(name = "C_COURIER")
    var courier: Courier? = null// 快递员

    @ManyToOne
    @JoinColumn(name = "C_ORDER_ID")
    var order: Order? = null // 订单

}
