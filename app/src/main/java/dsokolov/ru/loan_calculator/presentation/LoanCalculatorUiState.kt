package dsokolov.ru.loan_calculator.presentation

import androidx.compose.runtime.Immutable

sealed interface LoanCalculatorUiState {
    @Immutable
    data object Loading : LoanCalculatorUiState
    @Immutable
    data class Error(
        val loanSum: Int,
        val daysPeriod: Int,
    ) : LoanCalculatorUiState
    @Immutable
    data class Success(
        val loanSum: Int,
        val daysPeriod: Int,
        val interestRate: Double?,
        val refundedAmount: Double?,
        val repaymentDate: String?,
        val isCalculation: Boolean,
    ) : LoanCalculatorUiState
}
