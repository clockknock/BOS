import com.itheima.bos.crm.dao.CustomerDao
import com.itheima.bos.crm.domain.Customer
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
open class TestConstructor{

    @Test
    fun findCustomerFixedAreaIdIsNull(){
        val users = dao.findByFixedAreaIdIsNull()
        for (user in users){
            println(user)
        }
    }

    @Test
    fun testCustomer(){
        val customers = arrayListOf(Customer(1, "zs"), Customer(2, "ls"))
        for (c in customers){
            println(c)
        }
    }

    @Autowired lateinit var dao: CustomerDao
}