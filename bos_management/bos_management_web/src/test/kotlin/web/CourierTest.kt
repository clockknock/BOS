package web

import com.itheima.bos.domain.base.Courier
import com.itheima.bos.domain.base.Standard
import com.itheima.bos.service.base.CourierService
import com.itheima.bos.service.base.impl.CourierServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import java.util.ArrayList


/**
 * Created by 钟未鸣 on 2017/11/17 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
open class CourierTest {
    @Autowired lateinit var service: CourierService

    @Test
    fun queryWithCriteria() {

        val standard = Standard()
        standard.name = ""
        val courier = Courier("", "", "集团", standard)
        val courierNum = courier.courierNum
        val company = courier.company
        val type = courier.type


        val spec = Specification<Courier> { root, _, cb ->
            //动态根据页面提交的参数封装查询条件
            //用于收集查询条件对象
            val list = ArrayList<Predicate>()
            if (!company.isNullOrBlank()) {
                //需要条件一个过滤条件，根据公司模糊查询
                val p1 = cb.like(root.get<Any>("company").`as`(String::class.java), "%$company%")
                //where company like ?
                list.add(p1)
            }
            if (courierNum!!.isNotBlank()) {
                //需要条件一个过滤条件，根据工号等值查询
                val p2 = cb.like(root.get<Any>("courierNum").`as`(String::class.java),
                        courierNum)//where company like ?
                list.add(p2)
            }
            if (type != null) {
                println("type is not blank")
                //需要条件一个过滤条件，根据快递员类型等值查询
                val p3 = cb.equal(root.get<Any>("type").`as`(String::class.java), "%$type%")//where
                // company like ?
                list.add(p3)
            }
            //需要条件一个过滤条件，根据收派标准名称等值查询
            val join = root.join<Any, Any>("standard")
            val p4 = cb.like(join.get<String>("name").`as`(String::class.java), standard.name)
            //where company like ?
            list.add(p4)

            if (list.size == 0) {
                //没有过滤条件
                return@Specification null
            }

            cb.and(*list.toTypedArray())

        }
        val request = PageRequest(0, 3)
        val page = service.pageQuery(spec, request)
        println(page.content)
    }


}