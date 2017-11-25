import org.testng.annotations.Test

/**
 * Created by 钟未鸣 on 2017/11/24 .
 */
class TestIsNull{
    @Test
    fun isNull(){
        val isNull:String?=null
        println("isNull")
        println("isNullOrEmpty:${isNull.isNullOrEmpty()}")
        println("isNullOrBlank:${isNull.isNullOrBlank()}")
        println("===============================================")


        val isEmpty=""
        println("isEmpty")
        println("isNullOrEmpty:${isEmpty.isNullOrEmpty()}")
        println("isNullOrBlank:${isEmpty.isNullOrBlank()}")
        println("===============================================")

        val isSpace=" "
        println("isSpace")
        println("isNullOrEmpty:${isSpace.isNullOrEmpty()}")
        println("isNullOrBlank:${isSpace.isNullOrBlank()}")
    }

    @Test
    fun testEqual(){
        val s1="abc"
        val s2="ABc"
        println(s1.equals(s2,true))
        println(s1.equals(s2,false))
        println(s1.equals(s2))
    }

}