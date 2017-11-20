package com.itheima.bos.domain.base

import javax.persistence.*

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
@Entity
@Table(name = "T_SUB_AREA")
class SubArea {

    @Id
    @Column(name = "C_ID")
    var id: String? = null
    @Column(name = "C_START_NUM")
    var startNum: String? = null // 起始号
    @Column(name = "C_ENDNUM")
    var endNum: String? = null // 终止号
    @Column(name = "C_SINGLE")
    var single: Char? = null // 单双号
    @Column(name = "C_KEY_WORDS")
    var keyWords: String? = null // 关键字
    @Column(name = "C_ASSIST_KEY_WORDS")
    var assistKeyWords: String? = null // 辅助关键字

    @ManyToOne
    @JoinColumn(name = "C_AREA_ID")
    var area: Area? = null // 区域
    @ManyToOne
    @JoinColumn(name = "C_FIXEDAREA_ID")
    var fixedArea: FixedArea? = null // 定区

}