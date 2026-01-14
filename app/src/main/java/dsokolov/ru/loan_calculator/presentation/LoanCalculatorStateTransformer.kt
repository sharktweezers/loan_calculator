package dsokolov.ru.loan_calculator.presentation

import dsokolov.ru.loan_calculator.core.string_provider.StringProvider
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState
import dsokolov.ru.loan_calculator.R
import dsokolov.ru.loan_calculator.core.ext.PERCENT
import dsokolov.ru.loan_calculator.core.ext.SPACE
import dsokolov.ru.loan_calculator.core.ext.USD
import dsokolov.ru.loan_calculator.core.format.AmountFormatter

class LoanCalculatorStateTransformer(
    private val stringProvider: StringProvider
) {
    fun transform(mviState: LoanCalculatorState) = when(mviState) {
        is LoanCalculatorState.Empty -> LoanCalculatorUiState.Empty
        is LoanCalculatorState.Loading -> LoanCalculatorUiState.Loading
        is LoanCalculatorState.FilledLoanCalculatorState -> {
            LoanCalculatorUiState.FilledLoanCalculatorState(
                amount = mviState.amount,
                daysPeriod = mviState.daysPeriod,
                title = stringProvider.getString(R.string.loan_calculator_screen_title),
                amountTitle = stringProvider.getString(R.string.loan_calculator_amount_title),
                amountValue = AmountFormatter.format(
                    amount = mviState.amount,
                    suffix = "$SPACE$USD",
                ),
                daysPeriodTitle = stringProvider.getString(R.string.loan_calculator_loan_term_title),
                daysPeriodValue = AmountFormatter.format(
                    amount = mviState.daysPeriod,
                    suffix = SPACE + stringProvider.getString(R.string.loan_calculator_loan_term_suffix),
                ),
                minRangeAmount = mviState.minRangeAmount,
                maxRangeAmount = mviState.maxRangeAmount,
                minRangeDaysPeriod = mviState.minRangeDaysPeriod,
                maxRangeDaysPeriod = mviState.maxRangeDaysPeriod,
                stepCountDaysPeriod = mviState.stepCountDaysPeriod,
                transaction = mviState.transaction,
                interestRateTitle = stringProvider.getString(R.string.loan_calculator_loan_interest_rate_title),
                interestRateValue = AmountFormatter.format(
                    amount = mviState.interestRate,
                    suffix = "$SPACE$PERCENT",
                ),
                loanRepaymentAmountTitle = stringProvider.getString(R.string.loan_calculator_loan_repayment_title),
                loanRepaymentAmountValue = AmountFormatter.format(
                    amount = mviState.loanRepaymentAmount,
                    suffix = "$SPACE$USD",
                ),
                repaymentDateTitle = stringProvider.getString(R.string.loan_calculator_loan_repayment_date_title),
                repaymentDateValue = mviState.repaymentDate.orEmpty(),
                applyBtnTitle = stringProvider.getString(R.string.loan_calculator_loan_apply),
                errorMsg = mviState.errorMsg,
            )
        }
    }
}