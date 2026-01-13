package dsokolov.ru.loan_calculator.di

import dagger.Module
import dagger.Provides
import dsokolov.ru.loan_calculator.LoanCalculatorApplication
import dsokolov.ru.loan_calculator.core.StringProvider
import javax.inject.Singleton

@Module
class CoreModule {
    @Singleton
    @Provides
    fun provideStringProvider(
        loanCalculatorApplication: LoanCalculatorApplication
    ): StringProvider {
        return StringProvider(loanCalculatorApplication.applicationContext)
    }
}