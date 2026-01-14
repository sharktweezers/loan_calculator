package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import dsokolov.ru.loan_calculator.core.domain.ro.LoanCalculationRo
import dsokolov.ru.loan_calculator.core.repository.LoanCalculatorRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplyLoanCalculatorUseCase @Inject constructor(
    private val loanCalculatorRepository: LoanCalculatorRepository,
) {
    suspend operator fun invoke(
        amount: Int,
        period: Int,
        totalRepayment: Int,
    ): LoanCalculatorTransaction {
        delay(3000L) // имитация долгой загрузки по сети
        return loanCalculatorRepository.postLoanCalculator(
            ro = LoanCalculationRo(
                amount = amount,
                period = period,
                totalRepayment = totalRepayment,
            )
        )
    }
}