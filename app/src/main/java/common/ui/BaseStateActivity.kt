package common.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import common.*

abstract class BaseStateActivity : AppCompatActivity(), StateMachineNavigator {
    private lateinit var stateMachine: StateMachine<State, Event, SideEffect>

    abstract fun provideGraphBuilder(): StateMachine.GraphBuilder<State, Event, SideEffect>

    abstract fun doTransition(fromState: State, toState: State, event: Event, sideEffect: SideEffect?)

    private fun jumpToState(state: State): StateMachine<State, Event, SideEffect> {
        return stateMachine.with { initialState(state) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateMachine = createStateMachine()
    }

    override fun changeStateWithEvent(state: State, event: Event) {
        jumpToState(state).transition(event)
    }

    override fun changeEvent(event: Event) {
        stateMachine.transition(event)
    }

    override fun changeState(state: State) {
        jumpToState(state)
    }
    
    private fun createStateMachine(): StateMachine<State, Event, SideEffect> {
        return StateMachine.create(
            provideGraphBuilder().apply {
                onTransition {
                    val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
                    validTransition.apply {
                        doTransition(fromState, toState, event, sideEffect)
                    }
                }
            }
        )
    }
}