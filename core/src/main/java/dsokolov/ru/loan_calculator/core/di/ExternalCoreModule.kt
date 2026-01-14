package dsokolov.ru.loan_calculator.core.di

import dagger.Binds
import dagger.Module
import dsokolov.ru.loan_calculator.core.string_provider.StringProvider
import dsokolov.ru.loan_calculator.core.string_provider.StringProviderImpl

@Module
abstract class ExternalCoreModule {
    @Binds
    abstract fun bindStringProvider(
        stringProviderImpl: StringProviderImpl,
    ): StringProvider
}