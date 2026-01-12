package dsokolov.ru.loan_calculator.mvi

import dsokolov.ru.loan_calculator.presentation.LoanCalculatorUiState
import javax.inject.Inject

class LoanCalculatorStore @Inject constructor() {
    fun getInitialState() = LoanCalculatorUiState.Loading
}