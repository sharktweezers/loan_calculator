package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorFactory
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import javax.inject.Inject

class SaveLoanCalculatorPreferUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(amount: Int, period: Int): LoanCalculator {
        preferencesRepository.saveLoanCalculatorPrefer(
            amount = amount,
            period = period,
        )

        return LoanCalculatorFactory.createLoanCalculator(
            amount = amount,
            period = period,
        )
    }
}