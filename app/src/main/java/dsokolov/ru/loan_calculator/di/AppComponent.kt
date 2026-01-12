package dsokolov.ru.loan_calculator.di

import dagger.Component
import dsokolov.ru.loan_calculator.LoanCalculatorActivity
import javax.inject.Singleton

@Component(
    modules = [BindsViewModelModule::class],
    dependencies = [AppDeps::class],
)
@Singleton
interface AppComponent : AppApi {
    fun inject(componentManager: Di.ComponentManager)
    fun inject(loanCalculatorActivity: LoanCalculatorActivity)
}