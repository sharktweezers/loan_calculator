package dsokolov.ru.loan_calculator.presentation

sealed interface LoanCalculatorUiSideEffect {
    class SuccessTransaction(val msg: String) : LoanCalculatorUiSideEffect
}