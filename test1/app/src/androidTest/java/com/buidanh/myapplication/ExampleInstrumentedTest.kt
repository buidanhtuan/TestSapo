package com.buidanh.myapplication

import android.view.View
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

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
    private val input2 = "1234"

    @Before
    fun onSetup() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.input1))
            .perform(ViewActions.typeText(input1), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.input2))
            .perform(ViewActions.typeText(input2), ViewActions.closeSoftKeyboard())
    }

    @Test
    fun onCheckValueEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.input1))
            .check(ViewAssertions.matches(isEditTextValueDouble()))
        Espresso.onView(ViewMatchers.withId(R.id.input2))
            .check(ViewAssertions.matches(isEditTextValueDouble()))
    }

    @Test
    fun actionButtonAdd() {
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.output))
            .check(ViewAssertions.matches(ViewMatchers.withText("${input1.toInt() + input2.toInt()}")))
    }

    private fun isEditTextValueDouble(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item is EditText) {
                    val value = item.text.toString()
                    return try {
                        value.toInt()
                        true
                    } catch (e: Exception) {
                        false
                    }
                }
                return false
            }

        }

    }
}
