package dsokolov.ru.loan_calculator.remote.di

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dsokolov.ru.loan_calculator.remote.network.LOAN_CALCULATOR_BASE_URL
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorApi
import dsokolov.ru.loan_calculator.remote.network.loan_calculator.LoanCalculatorMockInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ExternalRemoteModule {
    @Provides
    @Singleton
    fun provideLoanCalculatorRetrofit(
        appContext: Context,
    ): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(LoanCalculatorMockInterceptor(appContext)).
            addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
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