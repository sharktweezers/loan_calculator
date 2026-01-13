package dsokolov.ru.loan_calculator.mvi

import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState
import javax.inject.Inject

class LoanCalculatorStore @Inject constructor() {
    fun getInitialState() = LoanCalculatorState.Loading
}