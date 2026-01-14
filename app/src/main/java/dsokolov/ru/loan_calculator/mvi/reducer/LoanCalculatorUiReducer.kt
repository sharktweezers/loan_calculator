package dsokolov.ru.loan_calculator.mvi.reducer

import dsokolov.ru.loan_calculator.mvi_core.ReducerDsl
import dsokolov.ru.loan_calculator.mvi_core.Update
import dsokolov.ru.loan_calculator.mvi.command.LoanCalculatorCommand as Command
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent.LoanCalculatorEventUi as UiEvent
import dsokolov.ru.loan_calculator.mvi.side_effect.LoanCalculatorSideEffect as SideEffect
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState as State

class LoanCalculatorUiReducer() : ReducerDsl<UiEvent, State, SideEffect, Command>() {
    override fun update(
        state: State,
        event: UiEvent
    ): Update<State, SideEffect, Command> {
        val filledState = state as? State.FilledLoanCalculatorState ?: return nothing()
        if (filledState.isTransaction) return nothing()

        return when (event) {
            is UiEvent.AmountChanged -> reduceAmountChanged(filledState, event)
            is UiEvent.PeriodChanged -> reducePeriodChanged(filledState, event)
        }
    }

    private fun reduceAmountChanged(
        state: State.FilledLoanCalculatorState,
        event: UiEvent.AmountChanged,
    ): Update<State, SideEffect, Command> {
        val newAmount = event.amount.toInt()

        command {
            Command.UpdateLoanCalculatorPreference(
                amount = newAmount,
                period = state.daysPeriod,
            )
        }

        return buildUpdate(state)
    }

    private fun reducePeriodChanged(
        state: State.FilledLoanCalculatorState,
        event: UiEvent.PeriodChanged,
    ): Update<State, SideEffect, Command> {
        val newPeriod = event.period.toInt()

        command {
            Command.UpdateLoanCalculatorPreference(
                amount = state.amount,
                period = newPeriod,
            )
        }

        return buildUpdate(state)
    }
}