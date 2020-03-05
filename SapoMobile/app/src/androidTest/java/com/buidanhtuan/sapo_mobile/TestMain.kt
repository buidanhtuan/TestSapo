package epresso

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

class TestMain {
    val t = ""
    val t1 = "12234"
    val t2 = "123456789"
    val t3 = "abc@gmail.co"
    val t4 = "a@gmail.com"
    val t5 ="tuan123#"
    val t6 = "Tuan1234$"

    @Before
    fun onSetup() {
        ActivityScenario.launch(MainActivity::class.java)
    }
    @Test
    fun TestMainUserName(){
        //chua nhap username
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_user_name))
            .perform(ViewActions.typeText(t), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_user_name))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hãy nhập UserName")))
        //nhap username ngan
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_user_name))
            .perform(ViewActions.typeText(t1), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_user_name))
            .check(ViewAssertions.matches(ViewMatchers.withText("User name cần > 8 kí tự")))
        //nhap dung username
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_user_name))
            .perform(ViewActions.typeText(t2), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_user_name))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
    @Test
    fun TestMainEmail(){
        //chua nhap email
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_email))
            .perform(ViewActions.typeText(t), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_email))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hãy nhập Email")))
        //email sai dinh dang
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_email))
            .perform(ViewActions.typeText(t3), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_email))
            .check(ViewAssertions.matches(ViewMatchers.withText("Email chưa đúng định dạng")))
        //email dug dinh dang
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_email))
            .perform(ViewActions.typeText(t4), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_email))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
    @Test
    fun TestMainPassword(){
        //chua nhap password
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hãy nhập mật khẩu")))
        //mk sai
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t2), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("Mật khẩu chưa đúng định dạng")))
        //mk sai
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t5), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("Mật khẩu chưa đúng định dạng")))
        //mk đúng
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
    @Test
    fun CheckMainRepeatPassword(){
        //chưa nhập
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_repeat_password))
            .perform(ViewActions.typeText(t), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_repeat_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hãy nhập lại mật khẩu")))
        //nhập sai
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_repeat_password))
            .perform(ViewActions.typeText(t2), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_repeat_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("Chưa đúng mật khẩu")))
        //nhập đúng
        clearMain()
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_repeat_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_main_report_repeat_password))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
    @Test
    fun CheckMainButton() {
        Espresso.onView(ViewMatchers.withId(R.id.et_main_user_name))
            .perform(ViewActions.typeText(t2), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_email))
            .perform(ViewActions.typeText(t4), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_repeat_password))
            .perform(ViewActions.typeText(t6), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_city))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    fun clearMain(){
        Espresso.onView(ViewMatchers.withId(R.id.et_main_user_name))
            .perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_email))
            .perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_password))
            .perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_main_repeat_password))
            .perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard())
    }
}