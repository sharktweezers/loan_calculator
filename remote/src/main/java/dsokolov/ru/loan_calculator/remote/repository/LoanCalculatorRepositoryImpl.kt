package dsokolov.ru.loan_calculator.remote.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import dsokolov.ru.loan_calculator.core.repository.LoanCalculatorRepository
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorApi
import javax.inject.Inject

class LoanCalculatorRepositoryImpl @Inject constructor(
    val api: LoanCalculatorApi
) : LoanCalculatorRepository {
    override suspend fun getLoanCalculatorPrefer(): LoanCalculatorTransaction {
        TODO("Not yet implemented")
    }
}