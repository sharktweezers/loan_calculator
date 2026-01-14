package dsokolov.ru.loan_calculator.core.domain.models

sealed interface LoanCalculatorTransaction {
    data object Loading : LoanCalculatorTransaction
    data class Error(val error: String?) : LoanCalculatorTransaction
    data object Success : LoanCalculatorTransaction
}