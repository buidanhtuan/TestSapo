package com.buidanhtuan.sapo_mobile

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.buidanhtuan.sapo_mobile.activity.AgeActivity
import com.buidanhtuan.sapo_mobile.activity.MainActivity
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import com.shawnlin.numberpicker.NumberPicker
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import kotlin.math.absoluteValue
import androidx.core.view.size as size1

class Test {
    val t2 = "123456789"
    val t4 = "a@gmail.com"
    val t6 = "Tuan1234$"
    val position = 5
    var userName = ""
    var email = ""
    var age = ""
    var sex = ""
    var city = ""
    var district = ""
    @Before
    fun onSetup() {
        ActivityScenario.launch(MainActivity::class.java)
    }
    @Test
    fun CheckAll() {
        //Main
        Espresso.onView(ViewMatchers.withId(R.id.et_main_user_name))
            .perform(ViewActions.typeText(t2), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_email))
            .perform(ViewActions.typeText(t4), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_repeat_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        userName = getText(R.id.et_main_user_name)
        email = getText(R.id.et_main_email)
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_city))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //City
        city = getChildValue(R.id.rv_city,position,R.id.tv_city_adapter)
        Espresso.onView(ViewMatchers.withId(R.id.rv_city)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.rv_city)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.rv_city)).perform(RecyclerViewActions.
            actionOnItemAtPosition<CityAdapter.ViewHolder>(position, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_district))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //District
        district = getChildValue(R.id.rv_district,position,R.id.tv_district_adapter)
        Espresso.onView(ViewMatchers.withId(R.id.rv_district)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.rv_district)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.rv_district)).perform(
            RecyclerViewActions.
                actionOnItemAtPosition<CityAdapter.ViewHolder>(position, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.np_age))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //Age
        Espresso.onView(ViewMatchers.withId(R.id.np_age)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.np_age)).perform(ViewActions.swipeUp())
        age = getNumber(R.id.np_age)
        Espresso.onView(ViewMatchers.withId(R.id.radioButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.radioButton2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.radioButton3)).perform(ViewActions.click())
        sex = getSex(R.id.rg_age)
        Espresso.onView(ViewMatchers.withId(R.id.bt_age)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_summery_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //Summary
        Espresso.onView(ViewMatchers.withId(R.id.et_summary_user_name))
            .check(ViewAssertions.matches(ViewMatchers.withText(userName)))
        Espresso.onView(ViewMatchers.withId(R.id.et_summary_email))
            .check(ViewAssertions.matches(ViewMatchers.withText(email)))
        Espresso.onView(ViewMatchers.withId(R.id.et_summary_age))
            .check(ViewAssertions.matches(ViewMatchers.withText(age)))
        Espresso.onView(ViewMatchers.withId(R.id.et_summary_sex))
            .check(ViewAssertions.matches(ViewMatchers.withText(sex)))
        Espresso.onView(ViewMatchers.withId(R.id.et_summary_city))
            .check(ViewAssertions.matches(ViewMatchers.withText(city)))
        Espresso.onView(ViewMatchers.withId(R.id.et_summery_district))
            .check(ViewAssertions.matches(ViewMatchers.withText(district)))

        Espresso.onView(ViewMatchers.withId(R.id.bt_summary)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.imv_wellcome))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    fun getChildValue(RecyclerViewId: Int,position : Int, itemId: Int): String {
        var textItem = ""
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(view: View): Boolean {
                val viewItem: ViewGroup = (view as RecyclerView).layoutManager!!.findViewByPosition(position) as ViewGroup
                textItem = (viewItem.findViewById<TextView>(itemId)).text.toString()
                return true
            }
        }
        Espresso.onView(ViewMatchers.withId(RecyclerViewId)).check(ViewAssertions.matches(matcher))
        return textItem
    }
    fun getText(idEditText : Int):String{
        var text = ""
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(view: View): Boolean {
                val editText = view.findViewById<EditText>(idEditText)
                text = editText.text.toString()
                return true
            }
        }
        Espresso.onView(ViewMatchers.withId(idEditText)).check(ViewAssertions.matches(matcher))
        return text
    }
    fun getNumber(idNumberPicker : Int) : String{
        var nb = ""
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(view: View): Boolean {
                val numberPicker = view.findViewById<NumberPicker>(idNumberPicker)
                nb = numberPicker.value.toString()
                return true
            }
        }
        Espresso.onView(ViewMatchers.withId(idNumberPicker)).check(ViewAssertions.matches(matcher))
        return nb
    }
    fun getSex(idRadioGroup : Int) : String{
        var sex = ""
        val matcher: TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}
            override fun matchesSafely(view: View): Boolean {
                val radioGroup = view.findViewById<RadioGroup>(idRadioGroup)
                val checkedId = radioGroup.checkedRadioButtonId
                if(R.id.radioButton  ==checkedId) sex = "Nam"
                if(R.id.radioButton2 ==checkedId) sex = "Nữ"
                if(R.id.radioButton3 ==checkedId) sex = "Khác"
                return true
            }
        }
        Espresso.onView(ViewMatchers.withId(idRadioGroup)).check(ViewAssertions.matches(matcher))
        return sex
    }
}