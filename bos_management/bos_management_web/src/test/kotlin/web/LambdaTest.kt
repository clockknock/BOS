package web

import org.junit.Test

/**
 * Created by 钟未鸣 on 2017/11/27 .
 */
class LambdaTest {

    @Test
    fun testLambda() {
        val banker = Banker()
        Thread({
            kotlin.run { }
        }).start()
    }

    inner class Banker() {
        fun doCountA(count: Count) {
            count.count("a")
        }

        fun doCountB(msg: String, count: Count) {

        }
    }

    interface Count {
        fun count(str: String): String
    }
}