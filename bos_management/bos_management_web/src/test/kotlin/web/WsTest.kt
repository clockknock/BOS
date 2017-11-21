package web

import com.itheima.bos.crm.service.CustomerService
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
class WsTest {
    @Suppress("SpringKotlinAutowiring")
    @Autowired
    lateinit var service: CustomerService

    @Test
    fun testFindAll() {
        val findAll = service.findAll()
        for (customer in findAll) {
            println(customer)
        }
    }


}