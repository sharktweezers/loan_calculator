package dsokolov.ru.loan_calculator.remote.network.loan_calculator

import dsokolov.ru.loan_calculator.remote.models.LoanCalculationDto
import dsokolov.ru.loan_calculator.core.domain.ro.LoanCalculationRo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface LoanCalculatorApi {

    @POST(value = LOAN_CALCULATOR_PATH)
    fun applyPostCalculator(
        @Body request: LoanCalculationRo,
    ): Flow<LoanCalculationDto>

    @POST(value = LOAN_CALCULATOR_BAD_REQUEST_PATH)
    fun applyPostCalculatorWithBadParameter(
        @Body request: LoanCalculationRo,
    ): Flow<LoanCalculationDto>

    companion object {
        const val LOAN_CALCULATOR_PATH = "posts"
        const val LOAN_CALCULATOR_BAD_REQUEST_PATH = "bad_request_posts"
    }

}