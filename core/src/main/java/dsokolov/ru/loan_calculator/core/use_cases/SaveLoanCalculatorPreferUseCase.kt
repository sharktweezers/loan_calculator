package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorMapper
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveLoanCalculatorPreferUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val mapper: LoanCalculatorMapper,
) {
    suspend operator fun invoke(amount: Int, period: Int): LoanCalculator {
        preferencesRepository.saveLoanCalculatorPrefer(
            amount = amount,
            period = period,
        )

        return mapper.map(
            amount = amount,
            period = period,
        )
    }
}