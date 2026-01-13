package dsokolov.ru.loan_calculator.mvi.reducer

import dsokolov.ru.loan_calculator.mvi_core.Reducer
import dsokolov.ru.loan_calculator.mvi_core.Update
import dsokolov.ru.loan_calculator.mvi.command.LoanCalculatorCommand as Command
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent.LoanCalculatorEventUi as UiEvent
import dsokolov.ru.loan_calculator.mvi.side_effect.LoanCalculatorSideEffect as SideEffect
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState as State

class LoanCalculatorUiReducer() : Reducer<UiEvent, State, SideEffect, Command> {
    override fun update(
        state: State,
        event: UiEvent
    ): Update<State, SideEffect, Command> {
        return Update.nothing()
    }
}