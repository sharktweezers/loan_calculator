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
        return when (event) {
            is DomainEvent.ReceivedLoanCalculatorState -> reduceReceivedLoanCalculator(state, event)
        }
    }

    private fun reduceReceivedLoanCalculator(
        state: State,
        event: DomainEvent.ReceivedLoanCalculatorState
    ): Update<State, SideEffect, Command> {
        updateState {
            State.FilledLoanCalculatorState(
                amount = event.domainState.amount,
                minRangeAmount = event.domainState.minRangeAmount,
                maxRangeAmount = event.domainState.maxRangeAmount,
                daysPeriod = event.domainState.daysPeriod,
                minRangeDaysPeriod = event.domainState.minRangeDaysPeriod,
                maxRangeDaysPeriod = event.domainState.maxRangeDaysPeriod,
                stepCountDaysPeriod = event.domainState.stepCountDaysPeriod,
                isTransaction = false,
                interestRate = event.domainState.interestRate,
                loanRepaymentAmount = event.domainState.loanRepaymentAmount,
                repaymentDate = event.domainState.repaymentDate,
                errorMsg = null
            )
        }

        return buildUpdate(state)
    }
}