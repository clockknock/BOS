package web

import com.itheima.bos.domain.base.Area
import org.apache.commons.lang.math.RandomUtils
import org.apache.commons.lang3.RandomStringUtils
import org.apache.shiro.crypto.RandomNumberGenerator
import org.junit.Test

/**
 * Created by 钟未鸣 on 2017/11/20 .
 */
class DomainTest{
    @Test
    fun testLay(){
        val area = Area()
        area.province="gz"
        area.city="sz"
        area.district="ns"
        val randomNumeric = RandomStringUtils.randomNumeric(4)
        println(randomNumeric)
    }
}