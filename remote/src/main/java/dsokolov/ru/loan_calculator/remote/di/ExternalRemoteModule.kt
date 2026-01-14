package dsokolov.ru.loan_calculator.remote.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dsokolov.ru.loan_calculator.remote.network.LOAN_CALCULATOR_BASE_URL
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorApi
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorMockInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ExternalRemoteModule {
    @Provides
    @Singleton
    fun provideLoanCalculatorRetrofit(
        appContext: Context,
    ): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(LoanCalculatorMockInterceptor(appContext))
            .build()

        return Retrofit.Builder()
            .baseUrl(LOAN_CALCULATOR_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoanCalculatorApi(retrofitInstance: Retrofit): LoanCalculatorApi {
        return retrofitInstance.create(LoanCalculatorApi::class.java)
    }
}