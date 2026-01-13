package dsokolov.ru.loan_calculator.di

import dagger.Module
import dsokolov.ru.loan_calculator.injector.scope.PerFeature

@Module
class CalculatorModule {

    /*@Provides
    @PerFeature
    fun provideCountriesUiReducer() = CountriesUiReducer()

    @Provides
    @PerFeature
    fun provideCountriesDomainReducer() = CountriesDomainReducer()

    @Provides
    @PerFeature
    fun provideCountriesReducer(
        uiReducer: CountriesUiReducer,
        domainReducer: CountriesDomainReducer,
    ) = CountriesReducer(uiReducer, domainReducer)

    @Provides
    fun provideCountriesCommandHandler(
        avosendRepository: AvosendRepository,
        appConfigRepository: AppConfigRepository,
    ): CountriesCommandHandler {
        return CountriesCommandHandler(avosendRepository, appConfigRepository)
    }

    @Provides
    fun providesCountriesTransitionListener(): CountriesTransitionListener {
        return CountriesTransitionListener()
    }

    @Provides
    fun providesCountriesStoreFactory(
        reducer: CountriesReducer,
        commandHandler: CountriesCommandHandler,
        transitionListener: CountriesTransitionListener
    ): CountriesStoreFactory {
        return CountriesStoreFactory(reducer, commandHandler, transitionListener)
    }

    @Provides
    @PerFeature
    fun provideCountriesUiConverter(stringProvider: StringProvider): CountriesUiConverter {
        return CountriesUiConverter(stringProvider)
    }

    @Provides
    @PerFeature
    fun provideCountriesUiStateFactory(converter: CountriesUiConverter): CountriesUiStateFactory {
        return CountriesUiStateFactory(converter)
    }

    @Provides
    @PerFeature
    fun provideCountriesUiSideEffectHandler(): CountriesUiSideEffectHandler {
        return CountriesUiSideEffectHandler()
    }*/
}