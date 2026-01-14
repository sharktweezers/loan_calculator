package dsokolov.ru.loan_calculator.remote.di

import dagger.Binds
import dagger.Module
import dsokolov.ru.loan_calculator.core.repository.PreferencesRepository
import dsokolov.ru.loan_calculator.remote.preferences.SharedPreferences
import dsokolov.ru.loan_calculator.remote.preferences.SharedPreferencesImpl
import dsokolov.ru.loan_calculator.remote.repository.PreferencesRepositoryImpl

@Module
abstract class ExternalRemoteModule {
    @Binds
    abstract fun bindSharedPreferences(
        stringProviderImpl: SharedPreferencesImpl,
    ): SharedPreferences

    @Binds
    abstract fun bindPreferencesRepository(
        stringProviderImpl: PreferencesRepositoryImpl,
    ): PreferencesRepository
}