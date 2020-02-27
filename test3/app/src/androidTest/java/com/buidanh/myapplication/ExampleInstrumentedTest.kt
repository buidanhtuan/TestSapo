package com.buidanh.myapplication

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.view.get
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
import com.buidanh.myapplication.R.id.text
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher
import java.util.regex.Pattern.matches


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("UNREACHABLE_CODE")
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
        Espresso.onView(ViewMatchers.withId(text)).check(ViewAssertions.matches(ViewMatchers.withText(getChild(R.id.recycle_view, 0))))
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

    fun getChild(@IdRes RecyclerViewId: Int,position : Int): String {
        var textItem = ""
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(v: View?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val recycle = (v as RecyclerView)
                //textItem = (recycle.layoutManager!!.findViewByPosition(position) as TextView).toString()
                //textItem = recycle.getChildAt(position).transitionName
                //textItem = recycle.layoutManager!!.getChildAt(position).toString()
                textItem = (recycle.layoutManager!!.findViewByPosition(position) as TextView).text.toString()
            }
        }
        onView(ViewMatchers.withId(RecyclerViewId)).check(ViewAssertions.matches(matcher))
        return textItem
    }
}
