package common

const val ADDITION = 1
const val SUBTRACTION = 2
const val MULTIPLICATION = 3
const val DIVISION = 4

sealed class State {
    object MainDashBoardState : State()
    object CalculateState : State()
    object AboutState: State()
}

sealed class Event {
    object EmptyEvent : Event()
    object AdditionEvent : Event()
    object SubtractionEvent : Event()
    object MultiplicationEvent : Event()
    object DivisionEvent : Event()
}

sealed class SideEffect {
    sealed class OperatorSideEffect : SideEffect() {
        object AdditionOperatorSideEffect : OperatorSideEffect()
        object SubtractionOperatorSideEffect : OperatorSideEffect()
        object MultiplicationOperatorSideEffect : OperatorSideEffect()
        object DivisionOperatorSideEffect : OperatorSideEffect()
    }
}

fun SideEffect.OperatorSideEffect.toOperator(): Int {
    return when (this) {
        SideEffect.OperatorSideEffect.AdditionOperatorSideEffect -> ADDITION
        SideEffect.OperatorSideEffect.SubtractionOperatorSideEffect -> SUBTRACTION
        SideEffect.OperatorSideEffect.MultiplicationOperatorSideEffect -> MULTIPLICATION
        SideEffect.OperatorSideEffect.DivisionOperatorSideEffect -> DIVISION
    }
}

fun Int.toOperatorText(): String {
    return when (this) {
        ADDITION -> "Addition"
        SUBTRACTION -> "Subtraction"
        MULTIPLICATION -> "Multiplication"
        DIVISION -> "Division"
        else -> ""
    }
}
