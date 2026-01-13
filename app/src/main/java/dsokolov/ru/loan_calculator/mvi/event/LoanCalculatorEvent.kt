package dsokolov.ru.loan_calculator.mvi.event

sealed interface LoanCalculatorEvent {
    sealed interface LoanCalculatorEventUi : LoanCalculatorEvent {

    }

    sealed interface LoanCalculatorEventDomain : LoanCalculatorEvent {

    }
}