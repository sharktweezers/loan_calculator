package dsokolov.ru.loan_calculator.core.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import dsokolov.ru.loan_calculator.core.domain.ro.LoanCalculationRo
import kotlinx.coroutines.flow.Flow

interface LoanCalculatorRepository {
    suspend fun postLoanCalculator(ro: LoanCalculationRo): Flow<LoanCalculatorTransaction>
}