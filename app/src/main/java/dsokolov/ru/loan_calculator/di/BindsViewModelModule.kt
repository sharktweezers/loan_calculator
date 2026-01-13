package dsokolov.ru.loan_calculator.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelKey
import dsokolov.ru.loan_calculator.presentation.LoanCalculatorViewModel
import dsokolov.ru.loan_calculator.injector.viewmodel.ViewModelFactoryModule

@Module(includes = [ViewModelFactoryModule::class])
abstract class BindsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoanCalculatorViewModel::class)
    abstract fun bindLoanCalculatorViewModel(viewModel: LoanCalculatorViewModel): ViewModel
}