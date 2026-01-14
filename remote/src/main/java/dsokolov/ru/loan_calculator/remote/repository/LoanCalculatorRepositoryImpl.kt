package dsokolov.ru.loan_calculator.remote.repository

import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorTransaction
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorTransactionMapper
import dsokolov.ru.loan_calculator.core.repository.LoanCalculatorRepository
import dsokolov.ru.loan_calculator.core.domain.ro.LoanCalculationRo
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanCalculatorRepositoryImpl @Inject constructor(
    private val api: LoanCalculatorApi,
    private val loanCalculatorTransactionMapper: LoanCalculatorTransactionMapper,
) : LoanCalculatorRepository {
    override suspend fun postLoanCalculator(
        ro: LoanCalculationRo,
    ): Flow<LoanCalculatorTransaction> {
        val dtoFlow = if (ro.amount > 0 && ro.period > 0) {
            api.applyPostCalculator(ro)
        } else {
            api.applyPostCalculatorWithBadParameter(ro)
        }

        return dtoFlow.map { dto ->
            loanCalculatorTransactionMapper.map(dto.result)
        }
    }
}