package com.buidanhtuan.sapo_mobile

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.buidanhtuan.sapo_mobile.activity.AgeActivity
import org.junit.Before
import org.junit.Test

class TestAgeActivity {
    @Before
    fun onSetup() {
        ActivityScenario.launch(AgeActivity::class.java)
    }
    @Test
    fun ageActivity(){
        Espresso.onView(ViewMatchers.withId(R.id.np_age)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.np_age)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.radioButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.radioButton2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.radioButton3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bt_age)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_summery_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}