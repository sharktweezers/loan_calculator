package dsokolov.ru.loan_calculator.di

import dagger.Module
import dagger.Provides
import dsokolov.ru.loan_calculator.core.string_provider.StringProvider
import dsokolov.ru.loan_calculator.mvi.factory.LoanCalculatorStoreFactory
import dsokolov.ru.loan_calculator.mvi.handler.LoanCalculatorCommandHandler
import dsokolov.ru.loan_calculator.mvi.reducer.LoanCalculatorDomainReducer
import dsokolov.ru.loan_calculator.mvi.reducer.LoanCalculatorReducer
import dsokolov.ru.loan_calculator.mvi.reducer.LoanCalculatorUiReducer
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorStateTransformer

@Module
class CalculatorModule {
    @Provides
    fun provideLoanCalculatorUiReducer() = LoanCalculatorUiReducer()

    @Provides
    fun provideLoanCalculatorDomainReducer() = LoanCalculatorDomainReducer()

    @Provides
    fun provideLoanCalculatorReducer(
        uiReducer: LoanCalculatorUiReducer,
        domainReducer: LoanCalculatorDomainReducer,
    ): LoanCalculatorReducer {
        return LoanCalculatorReducer(uiReducer, domainReducer)
    }

    @Provides
    fun provideLoanCalculatorCommandHandler(): LoanCalculatorCommandHandler {
        return LoanCalculatorCommandHandler()
    }

    @Provides
    fun provideLoanCalculatorStoreFactory(
        reducer: LoanCalculatorReducer,
        commandHandler: LoanCalculatorCommandHandler,
    ): LoanCalculatorStoreFactory {
        return LoanCalculatorStoreFactory(reducer, commandHandler)
    }

    @Provides
    fun provideLoanCalculationStateTransformer(
        stringProvider: StringProvider
    ) = LoanCalculatorStateTransformer(
        stringProvider,
    )
}