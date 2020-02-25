package com.buidanh.test1

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import android.view.View
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        onView(withId(R.id.input1)).perform(typeText(input1), closeSoftKeyboard())
        onView(withId(R.id.input2)).perform(typeText(input2), closeSoftKeyboard())
    }

    @Test
    fun onCheckValueEditText() {
        onView(withId(R.id.input1)).check(matches(isEditTextValueDouble()))
        onView(withId(R.id.input2)).check(matches(isEditTextValueDouble()))
    }

    @Test
    fun actionButtonAdd() {
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.output)).check(matches(withText("${input1.toInt() + input2.toInt()}")))
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
