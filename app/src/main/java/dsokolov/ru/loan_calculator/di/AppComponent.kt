package dsokolov.ru.loan_calculator.di

import dagger.Component
import dsokolov.ru.loan_calculator.remote.di.ExternalRemoteBindsModule
import dsokolov.ru.loan_calculator.remote.di.ExternalRemoteModule
import dsokolov.ru.loan_calculator.core.di.ExternalCoreModule
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelFactoryStore
import javax.inject.Singleton

@Component(
    modules = [AppModule::class,
        ExternalRemoteBindsModule::class,
        ExternalRemoteModule::class,
        BindsViewModelModule::class,
        CalculatorModule::class,
        ExternalCoreModule::class
    ],
    dependencies = [AppDeps::class],
)
@Singleton
interface AppComponent : AppApi {
    fun inject(componentManager: Di.ComponentManager)
    fun inject(viewModelFactoryStore: ViewModelFactoryStore)
}