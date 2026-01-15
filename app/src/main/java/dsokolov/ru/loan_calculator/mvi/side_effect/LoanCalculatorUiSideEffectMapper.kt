package dsokolov.ru.loan_calculator.mvi.side_effect

import dsokolov.ru.loan_calculator.R
import dsokolov.ru.loan_calculator.core.string_provider.StringProvider
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorUiSideEffect

class LoanCalculatorUiSideEffectMapper(private val stringProvider: StringProvider) {
    fun map(mviSideEffect: LoanCalculatorSideEffect) = when (mviSideEffect) {
        LoanCalculatorSideEffect.SuccessTransaction -> {
            LoanCalculatorUiSideEffect.SuccessTransaction(
                stringProvider.getString(R.string.loan_calculator_success_apply)
            )
        }
    }
}