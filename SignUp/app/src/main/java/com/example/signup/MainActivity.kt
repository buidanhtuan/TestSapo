package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.signup.database.DatabaseHelper
import com.example.signup.screen.fragment.SignUp

class MainActivity : AppCompatActivity() {
    companion object{var userName = ""}
    private val fManager = supportFragmentManager
    private val fTransaction = fManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registerFragment = SignUp()
        fTransaction.add(R.id.frag,registerFragment)
        fTransaction.commit()
        DatabaseHelper.initDatabaseInstance(this)
    }
    fun setFragment(fragment: Fragment){
        val fragmentTransaction = fManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_from_right,
            R.anim.slide_out_to_left,
            R.anim.slide_in_from_left,
            R.anim.slide_out_to_right)
        fragmentTransaction.replace(R.id.frag,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}