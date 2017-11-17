package web

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.bos.domain.base.Standard
import org.junit.Test

/**
 * Created by 钟未鸣 on 2017/11/7 .
 */

open class JsonTest {
    data class Data(var total:Int,var rows: List<User>)
    data class User(var id: Int,var name: String,var address:String)

    @Test
    fun testJson(){
        val gson = Gson()
        User(1,"zs","1")
        User(2,"ls","2")
        User(3,"ww","3")
        val list = listOf( User(1,"zs","1"),User(2,"ls","2"),User(3,"ww","3"))
        val data = Data(list.size, list)
        val dataJson = gson.toJson(data)
        println(dataJson)
    }
    @Test
    fun testJsonObjList(){
        val gson = Gson()
        val s1 = Standard()
        s1.id=4
        s1.name="s1"
        s1.maxWeight=100
        s1.minWeight=20

        val s2 = Standard()
        s2.id=5
        s2.name="s2"
        s2.maxWeight=200
        s2.minWeight=10
        val list = arrayListOf(s1, s2)
        val toJson = gson.toJson(list)
//        val type =
        val standardList = gson.fromJson<List<Standard>>(toJson, object : TypeToken<ArrayList<Standard>>() {}.type)
        println("standardList:$standardList")

    }

}