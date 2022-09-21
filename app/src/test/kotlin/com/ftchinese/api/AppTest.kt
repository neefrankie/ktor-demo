/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.ftchinese.api

import kotlin.test.Test
import kotlin.test.assertNotNull

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

class AppTest {
    @Test fun appHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}
