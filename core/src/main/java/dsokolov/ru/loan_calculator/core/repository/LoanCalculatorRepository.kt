package dsokolov.ru.loan_calculator.core.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction

interface LoanCalculatorRepository {
    suspend fun getLoanCalculatorPrefer(): LoanCalculatorTransaction
}