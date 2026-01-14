package dsokolov.ru.loan_calculator.presentation

import androidx.compose.runtime.Immutable
import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction

sealed interface LoanCalculatorUiState {
    @Immutable
    data object Empty : LoanCalculatorUiState
    @Immutable
    data object Loading : LoanCalculatorUiState
    @Immutable
    data class FilledLoanCalculatorState(
        val amount: Int,
        val daysPeriod: Int,
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
        val transaction: LoanCalculatorTransaction,
        val interestRateTitle: String,
        val interestRateValue: String,
        val loanRepaymentAmountTitle: String,
        val loanRepaymentAmountValue: String,
        val repaymentDateTitle: String,
        val repaymentDateValue: String,
        val applyBtnTitle: String,
        val errorMsg: String?,
    ) : LoanCalculatorUiState
}
