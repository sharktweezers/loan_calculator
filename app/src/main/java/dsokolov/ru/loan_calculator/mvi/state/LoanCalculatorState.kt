package dsokolov.ru.loan_calculator.mvi.state

import androidx.compose.runtime.Immutable

sealed interface LoanCalculatorState {
    @Immutable
    data object Empty : LoanCalculatorState
    @Immutable
    data object Loading : LoanCalculatorState
    @Immutable
    data class Error(
        val loanSum: Int,
        val daysPeriod: Int,
    ) : LoanCalculatorState
    @Immutable
    data class Success(
        val loanSum: Int,
        val daysPeriod: Int,
        val interestRate: Double?,
        val refundedAmount: Double?,
        val repaymentDate: String?,
        val isCalculation: Boolean,
    ) : LoanCalculatorState
}