package com.buidanhtuan.sapo_mobile

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.activity.AgeActivity
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import com.buidanhtuan.sapo_mobile.activity.MainActivity
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Test
import java.util.regex.Pattern.matches

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