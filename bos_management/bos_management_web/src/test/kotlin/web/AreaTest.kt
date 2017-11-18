package web

import com.itheima.bos.domain.base.Area
import com.itheima.bos.utils.PinYin4jUtils
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.junit.Test
import java.io.File

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
class AreaTest {
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
}