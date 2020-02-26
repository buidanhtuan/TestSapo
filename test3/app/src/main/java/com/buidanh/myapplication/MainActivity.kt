package com.buidanh.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Sample activity to display a RecyclerView
 *
 * @author Guilherme Biff Zarelli
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userAdapter = createAdapter()
        userAdapter.submitList(USERS_DATA_SAMPLE)

        recycler_view.adapter = userAdapter
    }

    private fun createAdapter(): UserAdapter {
        return UserAdapter { view: View, user: User ->
            //TODO onClickListener
//            Snackbar.make(view, user.toString(), Snackbar.LENGTH_LONG).show()
            val intent: Intent = Intent (this, Main2Activity::class.java)
            var n:String = user.name
            intent.putExtra("Name", n)
            startActivity(intent)
        }
    }
}
