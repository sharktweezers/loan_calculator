package dsokolov.ru.loan_calculator.core.domain.models

class LoanCalculator(
    val amount: Int,
    val minRangeAmount: Int,
    val maxRangeAmount: Int,
    val daysPeriod: Int,
    val minRangeDaysPeriod: Int,
    val maxRangeDaysPeriod: Int,
    val stepCountDaysPeriod: Int,
    val interestRate: Double,
    val loanRepaymentAmount: Double,
    val repaymentDate: String,
)