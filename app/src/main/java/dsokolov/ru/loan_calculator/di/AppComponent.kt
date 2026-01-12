package dsokolov.ru.loan_calculator.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [],
    dependencies = [AppDeps::class],
)

@Singleton
interface AppComponent : AppApi {
    fun inject(componentManager: Di.ComponentManager)
}