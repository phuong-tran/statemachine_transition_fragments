package com.pt.statemachinedemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pt.statemachinedemo.R

import common.Event
import common.State
import common.ui.BaseStateFragment
import kotlinx.android.synthetic.main.fragment_main_dash_board.*

class MainDashboardFragment : BaseStateFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main_dash_board, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAdd.setOnClickListener {
            changeStateWithEvent(State.CalculateState, Event.AdditionEvent)
        }

        btnSubstract.setOnClickListener {
            changeStateWithEvent(State.CalculateState, Event.SubtractionEvent)
        }

        btnDivide.setOnClickListener {
            changeStateWithEvent(State.CalculateState, Event.DivisionEvent)
        }

        btnMultiply.setOnClickListener {
            changeStateWithEvent(State.CalculateState, Event.MultiplicationEvent)
        }

    }

    override fun provideState(): State = State.MainDashBoardState

    companion object {
        @JvmStatic
        fun newInstance() = MainDashboardFragment()
    }
}
