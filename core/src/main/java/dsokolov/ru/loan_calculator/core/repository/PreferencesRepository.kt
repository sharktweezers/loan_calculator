package dsokolov.ru.loan_calculator.core.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorPrefer

interface PreferencesRepository {
    suspend fun getLoanCalculatorPrefer(): LoanCalculatorPrefer

    suspend fun saveLoanCalculatorPrefer(amount: Int, period: Int)
}