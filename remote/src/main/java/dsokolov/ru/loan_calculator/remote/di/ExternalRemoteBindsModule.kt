package dsokolov.ru.loan_calculator.remote.di

import dagger.Binds
import dagger.Module
import dsokolov.ru.loan_calculator.core.repository.LoanCalculatorRepository
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import dsokolov.ru.loan_calculator.remote.preferences.SharedPreferences
import dsokolov.ru.loan_calculator.remote.preferences.SharedPreferencesImpl
import dsokolov.ru.loan_calculator.remote.repository.LoanCalculatorRepositoryImpl
import dsokolov.ru.loan_calculator.remote.repository.PreferencesRepositoryImpl

@Module
abstract class ExternalRemoteBindsModule {
    @Binds
    abstract fun bindSharedPreferences(
        stringProviderImpl: SharedPreferencesImpl,
    ): SharedPreferences

    @Binds
    abstract fun bindPreferencesRepository(
        stringProviderImpl: PreferencesRepositoryImpl,
    ): PreferencesRepository

    @Binds
    abstract fun bindLoanCalculatorRepository(
        stringProviderImpl: LoanCalculatorRepositoryImpl,
    ): LoanCalculatorRepository
}