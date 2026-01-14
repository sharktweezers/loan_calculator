package dsokolov.ru.loan_calculator.remote.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorPrefer
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import dsokolov.ru.loan_calculator.remote.preferences.SharedPreferences
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject internal constructor(
    private val sharedPreferences: SharedPreferences,
) : PreferencesRepository {
    override suspend fun getLoanCalculatorPrefer(): LoanCalculatorPrefer {
        return sharedPreferences.getLoanCalculatorPrefer()
    }

    override suspend fun saveLoanCalculatorPrefer(amount: Int, period: Int) {
        sharedPreferences.putLoanCalculatorPrefer(
            LoanCalculatorPrefer(
                amount = amount,
                daysPeriod = period,
            )
        )
    }
}