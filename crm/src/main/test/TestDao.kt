import com.itheima.bos.crm.dao.CustomerDao
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by 钟未鸣 on 2017/11/25 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
class TestDao{
    @Autowired lateinit var dao: CustomerDao

@Test fun testFindFixedAreaId(){
    val fixedAreaId = dao.findFixedAreaIdByAddress("北京市")
    println(fixedAreaId)
}
}