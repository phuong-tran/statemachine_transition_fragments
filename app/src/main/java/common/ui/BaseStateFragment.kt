package common.ui

import android.content.Context
import androidx.fragment.app.Fragment
import common.Event
import common.State
import common.StateMachineNavigator

abstract class BaseStateFragment : Fragment(), StateMachineNavigator {
    private var stateMachineNavigator: StateMachineNavigator? = null

    abstract fun provideState(): State

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StateMachineNavigator) {
            stateMachineNavigator = context
        } else {
            throw RuntimeException("$context must implement StateMachineNavigator")
        }
    }

    override fun onDetach() {
        super.onDetach()
        stateMachineNavigator = null
    }

    override fun changeStateWithEvent(state: State, event: Event) {
        stateMachineNavigator?.changeStateWithEvent(state, event)
    }

    override fun changeEvent(event: Event) {
        stateMachineNavigator?.changeEvent(event)
    }

    override fun changeState(state: State) {
        stateMachineNavigator?.changeState(state)
    }
}