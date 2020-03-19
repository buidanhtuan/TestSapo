package com.example.signup.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.signup.R
import com.example.signup.interfaces.OnClickItemListener

class WellcomeFragment:  Fragment(), OnClickItemListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wellcome, container, false)
    }
}