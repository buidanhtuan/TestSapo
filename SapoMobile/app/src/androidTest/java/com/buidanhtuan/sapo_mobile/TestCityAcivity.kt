package com.buidanhtuan.sapo_mobile

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import org.junit.Before
import org.junit.Test

class TestCityAcivity {
    @Before
    fun onSetup() {
        ActivityScenario.launch(CityActivity::class.java)
    }
    @Test
    fun CityActivity(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_city)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.rv_city)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.rv_city)).perform(
            RecyclerViewActions.
                actionOnItemAtPosition<CityAdapter.ViewHolder>(3, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_district))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}