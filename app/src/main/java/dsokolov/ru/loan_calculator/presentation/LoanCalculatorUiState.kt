package dsokolov.ru.loan_calculator.presentation

import androidx.compose.runtime.Immutable

sealed interface LoanCalculatorUiState {
    @Immutable
    data object Empty : LoanCalculatorUiState
    @Immutable
    data object Loading : LoanCalculatorUiState
    @Immutable
    data class FilledLoanCalculatorState(
        val title : String,
        val amountTitle: String,
        val amountValue: String,
        val daysPeriodTitle: String,
        val daysPeriodValue: String,
        val minRangeAmount: Int,
        val maxRangeAmount: Int,
        val minRangeDaysPeriod: Int,
        val maxRangeDaysPeriod: Int,
        val stepCountDaysPeriod: Int,
        val isTransaction: Boolean,
        val interestRateTitle: String?,
        val interestRateValue: String?,
        val refundedAmountTitle: String?,
        val refundedAmountValue: String?,
        val repaymentDateTitle: String?,
        val repaymentDateValue: String?,
        val errorMsg: String?,
    ) : LoanCalculatorUiState
}
