package dsokolov.ru.loan_calculator.remote.network.loan_calculator

import android.content.Context
import dsokolov.ru.loan_calculator.remote.network.LOAN_CALCULATOR_BASE_URL
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorApi.Companion.LOAN_CALCULATOR_BAD_REQUEST_PATH
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorApi.Companion.LOAN_CALCULATOR_PATH
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

internal class LoanCalculatorMockInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        when (path) {
            LOAN_CALCULATOR_BASE_URL + LOAN_CALCULATOR_PATH -> {
                val jsonString = context.assets.open("mock_response.json").bufferedReader().use { it.readText() }
                return Response.Builder()
                    .code(200)
                    .message("OK")
                    .protocol(Protocol.HTTP_1_1)
                    .request(request)
                    .body(jsonString.toResponseBody("application/json".toMediaTypeOrNull()))
                    .build()
            }
            LOAN_CALCULATOR_BASE_URL + LOAN_CALCULATOR_BAD_REQUEST_PATH -> {
                val jsonString = context.assets.open("mock_response_error.json").bufferedReader().use { it.readText() }
                return Response.Builder()
                    .code(200)
                    .message("OK")
                    .protocol(Protocol.HTTP_1_1)
                    .request(request)
                    .body(jsonString.toResponseBody("application/json".toMediaTypeOrNull()))
                    .build()
            }
        }

        return chain.proceed(request)
    }
}