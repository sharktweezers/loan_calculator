package dsokolov.ru.loan_calculator.remote.network.loan_calculator

import dsokolov.ru.loan_calculator.remote.models.LoanCalculationDto
import dsokolov.ru.loan_calculator.core.domain.ro.LoanCalculationRo
import retrofit2.http.Body
import retrofit2.http.POST

interface LoanCalculatorApi {

    @POST(value = LOAN_CALCULATOR_PATH)
    suspend fun applyPostCalculator(
        @Body request: LoanCalculationRo,
    ): LoanCalculationDto

    @POST(value = LOAN_CALCULATOR_BAD_REQUEST_PATH)
    suspend fun applyPostCalculatorWithBadParameter(
        @Body request: LoanCalculationRo,
    ): LoanCalculationDto

    companion object {
        const val LOAN_CALCULATOR_PATH = "posts"
        const val LOAN_CALCULATOR_BAD_REQUEST_PATH = "bad_request"
    }

}