package dsokolov.ru.loan_calculator.core.factory

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.ext.EMPTY
import dsokolov.ru.loan_calculator.core.format.LoanCalculatorDateFormatter

internal object LoanCalculatorFactory {
    fun createLoanCalculator(
        amount: Int,
        period: Int,
    ): LoanCalculator {
        val maxOverpaymentAmount  = amount * INTEREST_RATE / 100
        val currentOverpaymentAmount = period * maxOverpaymentAmount / MAX_RANGE_DAYS_PERIOD
        val loanRepaymentAmount = amount + currentOverpaymentAmount
        val repaymentDate = if (period > 0) {
            LoanCalculatorDateFormatter.format(period.toLong())
        } else EMPTY

        return LoanCalculator(
            amount = amount,
            minRangeAmount = MIN_RANGE_AMOUNT,
            maxRangeAmount = MAX_RANGE_AMOUNT,
            daysPeriod = period,
            minRangeDaysPeriod = MIN_RANGE_DAYS_PERIOD,
            maxRangeDaysPeriod = MAX_RANGE_DAYS_PERIOD,
            stepCountDaysPeriod = STEP_COUNT_DAYS_PERIOD,
            interestRate = INTEREST_RATE,
            loanRepaymentAmount = loanRepaymentAmount,
            repaymentDate = repaymentDate,
        )
    }

    internal const val MIN_RANGE_AMOUNT = 5000
    internal const val MAX_RANGE_AMOUNT = 50000
    internal const val MIN_RANGE_DAYS_PERIOD = 0
    internal const val MAX_RANGE_DAYS_PERIOD = 28
    internal const val STEP_COUNT_DAYS_PERIOD = 4
    internal const val INTEREST_RATE = 15.0
}