package com.pt.statemachinedemo.ui.activities.main

import android.os.Bundle
import com.pt.statemachinedemo.R
import base.Event
import base.SideEffect
import base.State
import base.StateMachine
import base.ui.BaseStateActivity

class MainActivity : BaseStateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeEvent(Event.EmptyEvent)
    }

    override fun provideGraphBuilder(): StateMachine.GraphBuilder<State, Event, SideEffect> {
        return provideGraphBuilderForMainActivity()
    }

    override fun handleState(
        fromState: State,
        toState: State,
        event: Event,
        sideEffect: SideEffect?
    ) = doStateHandler(
        fromState = fromState,
        toState = toState,
        event = event,
        sideEffect = sideEffect
    )
}
