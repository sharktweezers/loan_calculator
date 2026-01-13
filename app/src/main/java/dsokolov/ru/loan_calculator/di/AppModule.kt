package dsokolov.ru.loan_calculator.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dsokolov.ru.loan_calculator.LoanCalculatorApplication
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(loanCalculatorApplication: LoanCalculatorApplication): Context {
        return loanCalculatorApplication.applicationContext
    }
}