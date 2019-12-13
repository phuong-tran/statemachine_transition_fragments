package base

interface StateMachineNavigator {
    fun changeStateWithEvent(state: State, event: Event)
    fun changeEvent(event: Event)
    fun changeState(state: State)
}