package dsokolov.ru.loan_calculator.mvi.command

sealed interface LoanCalculatorCommand {
    data object GetInitFromPreferences : LoanCalculatorCommand
    class Apply() : LoanCalculatorCommand
}