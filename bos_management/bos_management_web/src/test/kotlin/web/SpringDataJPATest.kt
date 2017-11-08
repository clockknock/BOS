package web

import com.google.gson.ExclusionStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.itheima.bos.dao.base.StandardDao
import com.itheima.bos.domain.base.PageData
import com.itheima.bos.domain.base.Standard
import com.itheima.bos.service.base.StandardService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*
import com.google.gson.FieldAttributes



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

        for(x in 1..100){
            val standard = Standard()
            standard.name="标准"+x
            standard.minLength=Random().nextInt(100)
            standard.maxLength=standard.minLength+Random().nextInt(100)
            standard.minWeight=Random().nextInt(100)
            standard.maxWeight=standard.minLength+Random().nextInt(100)
            standard.operatingTime=Date()
            standard.operator="张"+x

            dao.save(standard)

        }
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

    @Test
    fun testFindByPage(){
        val request = PageRequest(2, 10)
        val page = dao.findAll(request)
        page.forEach { println(it) }

        val gson = /* GsonBuilder().setExclusionStrategies(
                object : ExclusionStrategy {
                    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                        return false
                    }

                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        //过滤掉字段名包含"id","address"的字段
                        val regex = Regex("max")
                        return f.name.contains("max",true)||f.name.contains("min",true)
                    }

                }).create()*/
        GsonBuilder().setExclusionStrategies(
                object : ExclusionStrategy{
                    override fun shouldSkipClass(clazz: Class<*>): Boolean {
                        return false
                    }

                    override fun shouldSkipField(f: FieldAttributes): Boolean {
                        val regex = Regex("max|min")
                       return regex.containsMatchIn(f.name)
                    }

                }
        ).create()

        val toJson = gson.toJson(PageData(page.totalElements, page.content))
        println(toJson)



//        page.forEach { println(it) }
    }

    @Autowired private lateinit var service: StandardService
    @Autowired private lateinit var dao: StandardDao
}