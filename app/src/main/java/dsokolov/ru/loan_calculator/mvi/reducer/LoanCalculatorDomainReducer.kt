package dsokolov.ru.loan_calculator.mvi.reducer

import dsokolov.ru.loan_calculator.mvi_core.ReducerDsl
import dsokolov.ru.loan_calculator.mvi_core.Update
import dsokolov.ru.loan_calculator.mvi.command.LoanCalculatorCommand as Command
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent.LoanCalculatorEventDomain as DomainEvent
import dsokolov.ru.loan_calculator.mvi.side_effect.LoanCalculatorSideEffect as SideEffect
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState as State

class LoanCalculatorDomainReducer() : ReducerDsl<DomainEvent, State, SideEffect, Command>() {
    override fun update(
        state: State,
        event: DomainEvent
    ): Update<State, SideEffect, Command> {
        return Update.nothing()
    }
}