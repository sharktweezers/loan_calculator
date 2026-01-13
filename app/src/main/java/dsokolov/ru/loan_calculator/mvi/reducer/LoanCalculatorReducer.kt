package dsokolov.ru.loan_calculator.mvi.reducer

import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState
import dsokolov.ru.loan_calculator.mvi_core.Reducer
import dsokolov.ru.loan_calculator.mvi_core.Update
import dsokolov.ru.loan_calculator.mvi.command.LoanCalculatorCommand as Command
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent as Event
import dsokolov.ru.loan_calculator.mvi.side_effect.LoanCalculatorSideEffect as SideEffect
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState as State

class LoanCalculatorReducer(
    private val uiReducer: LoanCalculatorUiReducer,
    private val domainReducer: LoanCalculatorDomainReducer,
) : Reducer<Event, State, SideEffect, Command> {
    override fun update(
        state: State,
        event: Event
    ): Update<State, SideEffect, Command> {
        return when (event) {
            is Event.LoanCalculatorEventUi -> uiReducer.update(state, event)
            is Event.LoanCalculatorEventDomain -> domainReducer.update(state, event)
        }
    }

    fun getInitialState(): State {
        return LoanCalculatorState.Loading
    }

    fun getInitialCommand(): List<Command> {
        return emptyList()
    }
}