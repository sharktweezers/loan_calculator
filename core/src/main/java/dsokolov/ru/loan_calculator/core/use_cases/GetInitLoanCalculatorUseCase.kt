package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.ext.EMPTY
import dsokolov.ru.loan_calculator.core.format.LoanCalculatorDateFormatter
import dsokolov.ru.loan_calculator.core.preferences.SharedPreferences
import javax.inject.Inject

class GetInitLoanCalculatorUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    suspend operator fun invoke() : LoanCalculator {
        val loanCalculatorPrefer = sharedPreferences.getLoanCalculatorPrefer()
        val loanRepaymentAmount = loanCalculatorPrefer.amount + (loanCalculatorPrefer.amount * INTEREST_RATE / 100)
        val repaymentDate = if (loanCalculatorPrefer.daysPeriod > 0) {
            LoanCalculatorDateFormatter.format(loanCalculatorPrefer.daysPeriod.toLong())
        } else EMPTY

        return LoanCalculator(
            amount = loanCalculatorPrefer.amount,
            minRangeAmount = MIN_RANGE_AMOUNT,
            maxRangeAmount = MAX_RANGE_AMOUNT,
            daysPeriod = loanCalculatorPrefer.daysPeriod,
            minRangeDaysPeriod = MIN_RANGE_DAYS_PERIOD,
            maxRangeDaysPeriod = MAX_RANGE_DAYS_PERIOD,
            stepCountDaysPeriod = STEP_COUNT_DAYS_PERIOD,
            interestRate = INTEREST_RATE,
            loanRepaymentAmount = loanRepaymentAmount,
            repaymentDate = repaymentDate,
        )
    }

    internal companion object {
        const val MIN_RANGE_AMOUNT = 5000
        const val MAX_RANGE_AMOUNT = 50000
        const val MIN_RANGE_DAYS_PERIOD = 0
        const val MAX_RANGE_DAYS_PERIOD = 28
        const val STEP_COUNT_DAYS_PERIOD = 4
        const val INTEREST_RATE = 15.0
    }
}