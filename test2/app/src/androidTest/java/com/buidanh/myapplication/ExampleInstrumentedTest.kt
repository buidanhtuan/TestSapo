package com.buidanh.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val input1 = "123"
    private val input2 = "456"

    @Before
    fun onSetup() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.input1))
            .perform(ViewActions.typeText(input1), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.input2))
            .perform(ViewActions.typeText(input2), ViewActions.closeSoftKeyboard())
    }

    @Test
    fun add() {
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.withText("${input1.toInt() + input2.toInt()}")))
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click())
    }
}
