import com.itheima.bos.crm.domain.Customer
import org.junit.Test

/**
 * Created by 钟未鸣 on 2017/11/21 .
 */
class TestConstructor{
    @Test
    fun testCustomer(){
        val customers = arrayListOf(Customer(1, "zs"), Customer(2, "ls"))
        for (c in customers){
            println(c)
        }
    }
}