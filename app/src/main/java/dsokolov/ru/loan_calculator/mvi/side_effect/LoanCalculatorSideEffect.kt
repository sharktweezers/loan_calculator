package dsokolov.ru.loan_calculator.mvi.side_effect

sealed interface LoanCalculatorSideEffect {
    data object SuccessTransaction : LoanCalculatorSideEffect
}