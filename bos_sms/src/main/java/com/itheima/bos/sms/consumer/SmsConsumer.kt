package com.itheima.bos.sms.consumer

import org.springframework.stereotype.Component
import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage

/**
 * Created by 钟未鸣 on 2017/11/27 .
 */
@Component
class SmsConsumer: MessageListener{
    override fun onMessage(p0: Message?) {
        val msg = p0 as TextMessage
        println("收到了fore发出的信息:${msg.text}")
    }

}