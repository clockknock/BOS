package com.itheima.bos.dao.base

import com.itheima.bos.domain.base.Area
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Created by 钟未鸣 on 2017/11/18 .
 */
interface AreaDao : JpaRepository<Area,String> {
//    fun findByProvinceOrCityOrDistrictOrShortcodeOrCitycodeLike(q: String) : List<Area>
    fun findByCitycodeLike(q: String) : List<Area>
    @Query("from Area where province like ?1 or city like ?1 or district like ?1 or shortcode " +
            "like ?1 or citycode like ?1")
    fun findByQ(q: String) : List<Area>

    fun findByProvinceAndCityAndDistrict(province: String?, city: String?, district: String?): Area
}