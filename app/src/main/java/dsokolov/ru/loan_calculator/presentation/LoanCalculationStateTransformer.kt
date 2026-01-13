package dsokolov.ru.loan_calculator.presentation

import dsokolov.ru.loan_calculator.core.StringProvider
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState

class LoanCalculationStateTransformer(
    private val stringProvider: StringProvider
) {
    fun transform(mviState: LoanCalculatorState) = when(mviState) {
        is LoanCalculatorState.Empty -> LoanCalculatorUiState.Empty
        is LoanCalculatorState.Loading -> LoanCalculatorUiState.Loading
        is LoanCalculatorState.FilledLoanCalculatorState -> {

        }
    }
}