package dsokolov.ru.loan_calculator.mvi.event

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction

sealed interface LoanCalculatorEvent {
    sealed interface LoanCalculatorEventUi : LoanCalculatorEvent {
        class AmountChanged(val amount: Float) : LoanCalculatorEventUi
        class PeriodChanged(val period: Float) : LoanCalculatorEventUi

        data object Apply : LoanCalculatorEventUi
    }

    sealed interface LoanCalculatorEventDomain : LoanCalculatorEvent {
        class ReceivedLoanCalculatorState(val domainState: LoanCalculator) : LoanCalculatorEventDomain
        class ReceiveLoanCalculatorTransaction(val transaction: LoanCalculatorTransaction) : LoanCalculatorEventDomain
    }
}