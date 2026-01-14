package dsokolov.ru.loan_calculator.core.mapper

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanCalculatorTransactionMapper @Inject constructor() {
    fun map(result: String?): LoanCalculatorTransaction {
        return if (result == "success") {
            LoanCalculatorTransaction.Success
        } else {
            LoanCalculatorTransaction.Error(result )
        }
    }
}