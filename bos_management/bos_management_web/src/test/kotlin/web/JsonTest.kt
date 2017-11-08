package web

import com.google.gson.Gson
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

}