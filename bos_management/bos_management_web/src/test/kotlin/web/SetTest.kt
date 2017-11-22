package web

import org.junit.Test

/**
 * Created by 钟未鸣 on 2017/11/22 .
 */
class SetTest {

    @Test
    fun testAdd() {
        var hs: Set<String> = hashSetOf("a", "b")
        hs = hashSetOf("c").plus(hs)
        println(hs)
    }
}