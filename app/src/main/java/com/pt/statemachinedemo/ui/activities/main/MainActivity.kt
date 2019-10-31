package com.pt.statemachinedemo.ui.activities.main

import android.os.Bundle
import com.pt.statemachinedemo.R
import common.Event
import common.SideEffect
import common.State
import common.StateMachine
import common.ui.BaseStateActivity

class MainActivity : BaseStateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeEvent(Event.EmptyEvent)
    }

    override fun provideGraphBuilder(): StateMachine.GraphBuilder<State, Event, SideEffect> {
        return graphBuilder()
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
