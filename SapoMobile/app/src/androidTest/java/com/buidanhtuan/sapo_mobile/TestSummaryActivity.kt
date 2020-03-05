package com.buidanhtuan.sapo_mobile

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.buidanhtuan.sapo_mobile.activity.MainActivity
import com.buidanhtuan.sapo_mobile.activity.SummaryActivity
import org.junit.Before
import org.junit.Test

class TestSummaryActivity {
    @Before
    fun onSetup() {
        ActivityScenario.launch(SummaryActivity::class.java)
    }
    @Test
    fun SummaryActivity(){
        Espresso.onView(ViewMatchers.withId(R.id.bt_summary)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_wellcome))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}