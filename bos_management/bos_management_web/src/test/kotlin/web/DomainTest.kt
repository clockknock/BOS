package web

import com.itheima.bos.domain.base.Area
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
    }
}