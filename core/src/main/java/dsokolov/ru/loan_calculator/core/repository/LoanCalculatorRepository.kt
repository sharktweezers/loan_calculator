package dsokolov.ru.loan_calculator.core.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import dsokolov.ru.loan_calculator.core.domain.ro.LoanCalculationRo

interface LoanCalculatorRepository {
    suspend fun postLoanCalculator(ro: LoanCalculationRo): LoanCalculatorTransaction
}