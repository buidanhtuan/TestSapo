package com.buidanh.myapplication

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.buidanh.myapplication.R.id.recycle_view
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Before
    fun onSetup(){
        ActivityScenario.launch(MainActivity::class.java)
    }
    @Test
    fun clickFistItem(){
        Espresso.onView(ViewMatchers.withId(recycle_view)).
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.RecyclerViewHolder>
                (0,ViewActions.click()))
        val text = getChild(withId(R.id.recycle_view), 0).toString()
        Espresso.onView(ViewMatchers.withId(R.id.text))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }
    @Test
    fun clickLastItem(){
        Espresso.onView(ViewMatchers.withId(recycle_view)).
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.RecyclerViewHolder>
                (getCountFromRecyclerView(recycle_view)-1,ViewActions.click()))
    }

    fun getCountFromRecyclerView(@IdRes RecyclerViewId: Int): Int {
        var count = 0
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun matchesSafely(item: View): Boolean {
                count = (item as RecyclerView).adapter!!.itemCount
                return true
            }
            override fun describeTo(description: Description) {}
        }
        onView(ViewMatchers.withId(RecyclerViewId)).check(ViewAssertions.matches(matcher))
        return count
    }

    fun getChild(parentMatcher: org.hamcrest.Matcher<View>, childPosition: Int): TypeSafeMatcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with $childPosition child view of type parentMatcher")
            }

            override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) {
                    return parentMatcher.matches(view.parent)
                }
                val group = view.parent as ViewGroup
                return parentMatcher.matches(view.parent) && group.getChildAt(childPosition) == view
            }
        }
    }

}
