package com.itheima.bos.web.action.base

import com.itheima.bos.service.base.AreaService
import com.itheima.bos.utils.PinYin4jUtils
import com.itheima.bos.web.action.common.CommonAction
import com.itheima.bos.domain.base.Area
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.apache.struts2.convention.annotation.ParentPackage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import java.io.File

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
@Controller
@Namespace("/area")
@ParentPackage("struts-default")
@Scope("prototype")
class AreaAction : CommonAction<Area>() {

    private val SUCCESS = "success"
    private val NONE = "none"
    private lateinit var areaFile: File
    fun setAreaFile(areaFile: File) {
        this.areaFile = areaFile
    }

    private  var q: String?=null
    fun setQ(q: String) {
        this.q = q
    }

    @Action(value = "importXls")
    fun importXls(): String {
        val wb = HSSFWorkbook(areaFile.inputStream())
        val areas = getAreas(wb)
        service.save(areas)
        return NONE
    }

    @Action(value = "pageQuery")
    fun pageQuery(): String {
        val pageRequest = PageRequest(page - 1, rows)
        val pageQuery = service.pageQuery(pageRequest)

        page2Json(pageQuery, Regex("subareas"))
        return NONE
    }

    @Action(value = "findAll")
    fun findAll(): String {
        val findAll:List<Area>
        if(q!=null){
          findAll=  service.findByQ(q!!)
        }else{
         findAll = service.findAll()

        }
//        findAll.forEach { println(it.name) }
        list2Json(findAll)
        return NONE
    }

    /**
     * 从上传xls中获取area的集合
     */
    private fun getAreas(wb: HSSFWorkbook): ArrayList<Area> {
        val sheet = wb.getSheetAt(0)

        val areas = arrayListOf<Area>()
        sheet.forEach rows@ {
            if (it.rowNum == 0) return@rows
            val province = it.getCell(1).stringCellValue
            val city = it.getCell(2).stringCellValue
            val district = it.getCell(3).stringCellValue
            val area = Area()
            area.id = it.getCell(0).stringCellValue
            area.province = province
            area.city = city
            area.district = district
            area.postcode = it.getCell(4).stringCellValue
            area.shortcode = PinYin4jUtils.getHeadByString(province + city + district).joinToString("")
            area.citycode = PinYin4jUtils.hanziToPinyin(city, "")

            areas.add(area)
        }
        return areas
    }

    @Autowired private lateinit var service: AreaService
}