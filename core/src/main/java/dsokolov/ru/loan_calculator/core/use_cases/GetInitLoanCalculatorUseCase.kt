package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculator
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorMapper
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetInitLoanCalculatorUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val mapper: LoanCalculatorMapper
) {
    suspend operator fun invoke() : LoanCalculator {
        val loanCalculatorPrefer = preferencesRepository.getLoanCalculatorPrefer()
        return mapper.map(
            amount = loanCalculatorPrefer.amount,
            period = loanCalculatorPrefer.daysPeriod,
        )
    }
}