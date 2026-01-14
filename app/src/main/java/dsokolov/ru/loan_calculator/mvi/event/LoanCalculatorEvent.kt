package dsokolov.ru.loan_calculator.mvi.event

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator

sealed interface LoanCalculatorEvent {
    sealed interface LoanCalculatorEventUi : LoanCalculatorEvent {
        class AmountChanged(val amount: Float) : LoanCalculatorEventUi
        class PeriodChanged(val period: Float) : LoanCalculatorEventUi
    }

    sealed interface LoanCalculatorEventDomain : LoanCalculatorEvent {
        class ReceivedLoanCalculatorState(val domainState: LoanCalculator) : LoanCalculatorEventDomain
    }
}