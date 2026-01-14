package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorFactory
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import javax.inject.Inject

class GetInitLoanCalculatorUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke() : LoanCalculator {
        val loanCalculatorPrefer = preferencesRepository.getLoanCalculatorPrefer()
        return LoanCalculatorFactory.createLoanCalculator(
            amount = loanCalculatorPrefer.amount,
            period = loanCalculatorPrefer.daysPeriod,
        )
    }
}