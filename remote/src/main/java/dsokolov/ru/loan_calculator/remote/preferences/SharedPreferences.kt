package dsokolov.ru.loan_calculator.remote.preferences

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorPrefer

interface SharedPreferences {
    suspend fun putLoanCalculatorPrefer(loanCalculatorPrefer: LoanCalculatorPrefer)

    suspend fun getLoanCalculatorPrefer(): LoanCalculatorPrefer
}