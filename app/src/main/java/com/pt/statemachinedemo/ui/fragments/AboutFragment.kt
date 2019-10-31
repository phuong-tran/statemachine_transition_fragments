package com.pt.statemachinedemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pt.statemachinedemo.R
import common.ui.BaseStateFragment

class AboutFragment : BaseStateFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.about, container, false)

    companion object {
        fun newInstance() = AboutFragment()
    }
}