package dsokolov.ru.loan_calculator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dsokolov.ru.loan_calculator.di.viewmodel.DaggerViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dsokolov.ru.loan_calculator.di.viewmodel.ViewModelKey
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorViewModel

@Module
abstract class BindsViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: DaggerViewModelFactory,
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoanCalculatorViewModel::class)
    abstract fun bindLoanCalculatorViewModel(viewModel: LoanCalculatorViewModel): ViewModel
}