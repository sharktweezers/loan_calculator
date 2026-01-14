package dsokolov.ru.loan_calculator.core.domain.models

sealed interface LoanCalculatorTransaction {
    data object Loading : LoanCalculatorTransaction
    class Error(val error: String) : LoanCalculatorTransaction
    data object Success : LoanCalculatorTransaction
}