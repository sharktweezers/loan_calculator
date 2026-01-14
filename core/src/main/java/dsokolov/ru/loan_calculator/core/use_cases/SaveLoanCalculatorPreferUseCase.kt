package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorPrefer
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorFactory
import dsokolov.ru.loan_calculator.core.preferences.SharedPreferences
import javax.inject.Inject

class SaveLoanCalculatorPreferUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    suspend operator fun invoke(amount: Int, period: Int): LoanCalculator {
        sharedPreferences.putLoanCalculatorPrefer(
            LoanCalculatorPrefer(
                amount = amount,
                daysPeriod = period,
            )
        )

        return LoanCalculatorFactory.createLoanCalculator(
            amount = amount,
            period = period,
        )
    }
}