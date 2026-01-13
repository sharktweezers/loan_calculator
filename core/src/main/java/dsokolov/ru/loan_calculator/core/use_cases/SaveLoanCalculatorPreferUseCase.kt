package dsokolov.ru.loan_calculator.core.use_cases

import dsokolov.ru.loan_calculator.core.preferences.SharedPreferences
import javax.inject.Inject

class SaveLoanCalculatorPreferUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
)