package web

import com.alibaba.fastjson.JSONArray
import com.itheima.bos.dao.base.AreaDao
import com.itheima.bos.domain.base.Area
import com.itheima.bos.service.base.AreaService
import com.itheima.bos.utils.PinYin4jUtils
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.io.File

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:applicationContext.xml")
class AreaTest {

    @Test
    fun testDao(){
        val list = dao.findByQ("%BJ%")
//        val list:List<Area> = dao.findByCityLikeOrProvinceLike("%beij%")
        for(a in list){

            println(a)
        }
    }

    @Test
    fun test2Json(){
        val ja = JSONArray()
        ja.toJSONString()
        println()
    }

    @Test
    fun testPOI() {
        val areaFile = File("E:\\studyArea\\JAVAEE_study\\BOS\\bosDay04\\03_区域测试数据\\区域导入测试数据.xls")

        val wb = HSSFWorkbook(areaFile.inputStream())
        val sheet = wb.getSheetAt(0)

        val areas = ArrayList<Area>(sheet.lastRowNum)
        sheet.forEach rows@ {
            if (it.rowNum == 0) return@rows
            val province = it.getCell(1).stringCellValue
            val city = it.getCell(2).stringCellValue
            val district = it.getCell(3).stringCellValue
            val area = Area()
            area.id=it.getCell(0).stringCellValue
            area.province= province
            area.city= city
            area.district= district
            area.postcode=it.getCell(4).stringCellValue
             area.shortcode=PinYin4jUtils.getHeadByString(province+city+district).joinToString("")
            area.citycode=PinYin4jUtils.hanziToPinyin(city,"")

            areas.add(area)
        }
        for(area in areas){
            println(area)
        }
    }

    @Test
    fun strArr2Str(){
        val strs = arrayOf("q", "a", "e", "b")
        val joinToString = strs.joinToString("")
        println(joinToString)
    }

    @Autowired lateinit var service: AreaService
    @Autowired lateinit var dao: AreaDao
}