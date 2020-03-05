package com.buidanh.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.buidanh.myapplication.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("UNREACHABLE_CODE")
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Before
    fun onSetup() {
        ActivityScenario.launch(Button::class.java)
    }

    @Test
    fun clickFistItem() {
        onView(withId(R.id.button)).perform(ViewActions.click())
        val value = (getChildValue(R.id.recycle_view, 7, R.id.tv_name) as TextView).text.toString()
        onView(withId(R.id.recycle_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition
                <RecyclerViewAdapter.RecyclerViewHolder>(7, ViewActions.click())
            )
        onView(withId(R.id.text)).check(matches(withText(value)))
    }
    private fun getChildValue(RecyclerViewId: Int, position: Int, itemId: Int): View? {
        var viewItem: View? = null
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(view: View): Boolean {
                viewItem = (view as RecyclerView).layoutManager!!.findViewByPosition(position)
                    ?.findViewById(itemId)

                return true
            }
        }
        onView(withId(RecyclerViewId)).check(ViewAssertions.matches(matcher))
        return viewItem
    }
}
