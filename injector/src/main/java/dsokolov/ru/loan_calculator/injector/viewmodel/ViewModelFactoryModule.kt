package dsokolov.ru.loan_calculator.injector.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: DaggerViewModelFactory,
    ): ViewModelProvider.Factory
}