package dsokolov.ru.loan_calculator.mvi.event

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator

sealed interface LoanCalculatorEvent {
    sealed interface LoanCalculatorEventUi : LoanCalculatorEvent {

    }

    sealed interface LoanCalculatorEventDomain : LoanCalculatorEvent {
        class ReceivedLoanCalculatorState(val domainState: LoanCalculator) : LoanCalculatorEventDomain
    }
}