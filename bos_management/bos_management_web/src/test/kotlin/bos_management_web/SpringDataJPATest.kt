package bos_management_web

import com.itheima.bos.dao.base.StandardDao
import com.itheima.bos.domain.base.Standard
import com.itheima.bos.service.base.StandardService
import com.itheima.bos.service.base.impl.StandardServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
open class SpringDataJPATest {

    @Test //测试保存
    fun testJPASave() {
        val model = Standard()
        model.name="good"
        model.maxLength=100
        model.minLength=10
        service.save(model)
    }

    @Test //测试更新
    fun testJPAUpdate() {
        val model = Standard()
        model.name="good4"
        model.maxLength=100
        model.minLength=10
        service.save(model)
    }
    @Test //测试查询列表
    fun testJPAQueryList() {
        val findAll = dao.findAll()
        for (standard in findAll){
            println(standard)
        }
    }

    @Test //测试删除
    fun testJPADelete(){
        dao.delete(2)
    }

    @Test //根据id查找
    fun testJPAFindById(){
        println(dao.findOne(4))
    }

    @Test //根据name查找
    fun testJPAFindByName(){

        println(dao.findByName("good4"))
    }

    @Test //根据name查找相似
    fun testJPAFindByNameLike(){
        println(dao.findByNameLike("%good%"))
    }

    @Test //根据name查找没有的
    fun testJPAFindByNameIsNull(){
        println(dao.findByNameIsNull())
    }

    @Autowired private lateinit var service: StandardService
    @Autowired private lateinit var dao: StandardDao
}