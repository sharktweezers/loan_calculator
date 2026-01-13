package dsokolov.ru.loan_calculator.di

import dagger.Component
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelFactoryStore
import javax.inject.Singleton

@Component(
    modules = [BindsViewModelModule::class, CalculatorModule::class, CoreModule::class],
    dependencies = [AppDeps::class],
)
@Singleton
interface AppComponent : AppApi {
    fun inject(componentManager: Di.ComponentManager)
    fun inject(viewModelFactoryStore: ViewModelFactoryStore)
}