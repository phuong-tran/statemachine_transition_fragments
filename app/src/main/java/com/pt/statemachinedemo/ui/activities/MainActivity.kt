package com.pt.statemachinedemo.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pt.statemachinedemo.R
import com.pt.statemachinedemo.ui.fragments.MainDashboardFragment
import com.pt.statemachinedemo.ui.fragments.MathOperatorFragment
import common.*
import common.ui.BaseStateActivity

class MainActivity : BaseStateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeEvent(Event.EmptyEvent)
    }

    override fun provideGraphBuilder(): StateMachine.GraphBuilder<State, Event, SideEffect> {
        return StateMachine.GraphBuilder<State, Event, SideEffect>().apply {
            initialState(State.MainDashBoardState)
            state<State.MainDashBoardState> {
                on<Event.EmptyEvent> {
                    transitionTo(State.MainDashBoardState)
                }
            }

            state<State.CalculateState> {
                on<Event.AdditionEvent> {
                    transitionTo(
                        State.CalculateState,
                        SideEffect.OperatorSideEffect.AdditionOperatorSideEffect
                    )
                }

                on<Event.SubtractionEvent> {
                    transitionTo(
                        State.CalculateState,
                        SideEffect.OperatorSideEffect.SubtractionOperatorSideEffect
                    )
                }

                on<Event.MultiplicationEvent> {
                    transitionTo(
                        State.CalculateState,
                        SideEffect.OperatorSideEffect.MultiplicationOperatorSideEffect
                    )
                }

                on<Event.DivisionEvent> {
                    transitionTo(
                        State.CalculateState,
                        SideEffect.OperatorSideEffect.DivisionOperatorSideEffect
                    )
                }
            }

        }
    }

    override fun doTransition(
        fromState: State,
        toState: State,
        event: Event,
        sideEffect: SideEffect?
    ) {
        when (toState) {
            State.MainDashBoardState -> {
                addOrReplaceFragment(
                    fragment = MainDashboardFragment.newInstance(),
                    isAdd = true,
                    isAddToBackStack = false
                )
            }

            State.CalculateState -> {
                sideEffect?.apply {
                    if (this is SideEffect.OperatorSideEffect) {
                        addOrReplaceFragment(
                            fragment =
                            MathOperatorFragment.newInstance(toOperator()),
                            isAddToBackStack = true,
                            isAdd = false
                        )
                    }
                }
            }
        }
    }

    private fun addOrReplaceFragment(
        fragment: Fragment,
        isAddToBackStack: Boolean = false,
        backStackName: String? = null,
        isAdd: Boolean = false,
        tag: String? = null
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (isAdd) {
                if (tag.isNullOrBlank()) {
                    add(R.id.holder, fragment)
                } else {
                    add(R.id.holder, fragment, tag)
                }
            } else {
                replace(R.id.holder, fragment)
            }
            if (isAddToBackStack) addToBackStack(backStackName)
            commit()
        }
    }
}
