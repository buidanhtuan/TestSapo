package com.buidanh.myapplication

import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UiAutomatorTest {
    private val PACKAGE_NAME = "com.leduyanh.sapouitest"

    private val LAUNCH_TIMEOUT = 5000L

    private lateinit var device: UiDevice

    private val VALUE_TEST = "3"

    lateinit var context: Context

    @Before
    fun startMainActivityFromHomeScreen() {

        context = InstrumentationRegistry.getInstrumentation().targetContext
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        Assert.assertThat(launcherPackage, CoreMatchers.notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            PACKAGE_NAME)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        device.wait(
            Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun checkPreconditions() {
        Assert.assertThat(device, CoreMatchers.notNullValue())
    }

    @Test
    fun actionBtn() {
        val recyclerview: UiScrollable = UiScrollable(UiSelector())
        recyclerview.scrollTextIntoView(VALUE_TEST)
        recyclerview.waitForExists(5000)
        val item: UiObject = recyclerview
            .getChildByText(UiSelector().className(android.widget.TextView::class.java.name),VALUE_TEST)
        item.click()

        var result:UiObject = findObject(R.id.)
        Assert.assertEquals(VALUE_TEST, result.text)
    }

    fun findObject(resId: Int, pkgName: String = PACKAGE_NAME): UiObject {
        return device.findObject(UiSelector().resourceId("$pkgName:id/${getResourceName(resId)}"))
    }
    fun getResourceName(@IdRes resId: Int): String {
        return context.resources.getResourceEntryName(resId)
    }
}