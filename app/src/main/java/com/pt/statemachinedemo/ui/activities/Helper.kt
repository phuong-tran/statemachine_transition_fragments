package com.pt.statemachinedemo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pt.statemachinedemo.R
import com.pt.statemachinedemo.ui.fragments.MainDashboardFragment
import com.pt.statemachinedemo.ui.fragments.MathOperatorFragment
import common.*

fun MainActivity.graphBuilder(): StateMachine.GraphBuilder<State, Event, SideEffect> {
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

fun AppCompatActivity.addOrReplaceFragment(
    fragment: Fragment,
    isAddToBackStack: Boolean = false,
    backStackName: String? = null,
    isAdd: Boolean = false,
    tag: String? = null,
    holder: Int = R.id.holder
) {
    supportFragmentManager.beginTransaction().apply {
        if (isAdd) {
            if (tag.isNullOrBlank()) {
                add(holder, fragment)
            } else {
                add(holder, fragment, tag)
            }
        } else {
            replace(holder, fragment)
        }
        if (isAddToBackStack) addToBackStack(backStackName)
        commit()
    }
}


fun MainActivity.doStateHandler(
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


