package com.itheima.bos.web.action.base

import com.itheima.bos.domain.base.Standard
import com.opensymphony.xwork2.ActionSupport
import com.opensymphony.xwork2.ModelDriven

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
class StandardAction : ActionSupport(),ModelDriven<Standard>{
    private val model = Standard()
    override fun getModel(): Standard {
        return model
    }

}