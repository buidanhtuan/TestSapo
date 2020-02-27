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
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import com.buidanh.myapplication.R.id.*
import kotlinx.android.synthetic.main.activity_main2.view.*
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
        val text = getChildValue(recycle_view,0)
        Espresso.onView(ViewMatchers.withId(R.id.recycle_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition
            <RecyclerViewAdapter.RecyclerViewHolder>(0,ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.text)).check(ViewAssertions.matches(withText(text)))
    }
    @Test
    fun clickLastItem(){
        val text = getChildValue(recycle_view,0)
        val count = getCountFromRecyclerView(recycle_view)-1
        Espresso.onView(ViewMatchers.withId(recycle_view)).
            perform(RecyclerViewActions.actionOnItemAtPosition
            <RecyclerViewAdapter.RecyclerViewHolder>(count-1,ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.text)).check(ViewAssertions.matches(withText(text)))
    }

    fun getCountFromRecyclerView(RecyclerViewId: Int): Int {
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

    fun getChildValue(RecyclerViewId: Int,position : Int): String {
        var textItem = ""
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(view: View): Boolean {
                val viewItem: ViewGroup = (view as RecyclerView).layoutManager!!.findViewByPosition(position) as ViewGroup
                var view = viewItem.getChildAt(0)
                var i = 0
                while (view !is TextView){
                    i++
                    view = viewItem.getChildAt(i)
                }
                textItem = view.text.toString()
                return true
            }
        }
        Espresso.onView(ViewMatchers.withId(RecyclerViewId)).check(ViewAssertions.matches(matcher))
        return textItem
    }
}
