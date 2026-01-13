package dsokolov.ru.loan_calculator.mvi.state

sealed interface LoanCalculatorState {

    data object Empty : LoanCalculatorState

    data object Loading : LoanCalculatorState

    data class FilledLoanCalculatorState(
        val amount: Int,
        val minRangeAmount: Int,
        val maxRangeAmount: Int,
        val daysPeriod: Int,
        val minRangeDaysPeriod: Int,
        val maxRangeDaysPeriod: Int,
        val stepCountDaysPeriod: Int,
        val isTransaction: Boolean,
        val interestRate: Double?,
        val loanRepaymentAmount: Double?,
        val repaymentDate: String?,
        val errorMsg: String?,
    ) : LoanCalculatorState
}