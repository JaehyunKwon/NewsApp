package com.test.mobile.newsapp

import com.test.mobile.extensions.convertDateTime
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun convertDate() {
        val date = convertDateTime("2024-07-23T12:25:00Z")
        println("====$date====")
    }
}