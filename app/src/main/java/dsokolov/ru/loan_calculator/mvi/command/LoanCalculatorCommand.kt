package dsokolov.ru.loan_calculator.mvi.command

sealed interface LoanCalculatorCommand {
    data object GetInitFromPreferences : LoanCalculatorCommand

    class UpdateLoanCalculatorPreference(
        val amount: Int,
        val period: Int,
    ) : LoanCalculatorCommand

    class Apply(
        val amount: Int,
        val period: Int,
        val totalRepayment: Int,
    ) : LoanCalculatorCommand
}