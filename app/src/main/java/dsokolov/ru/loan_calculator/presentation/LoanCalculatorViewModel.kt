package dsokolov.ru.loan_calculator.presentation

import dsokolov.ru.loan_calculator.mvi.LoanCalculatorStore
import dsokolov.ru.loan_calculator.mvi_core.BaseMviViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LoanCalculatorViewModel @Inject constructor(
    private val store: LoanCalculatorStore,
) : BaseMviViewModel() {
    private val _stateFlow = MutableStateFlow<LoanCalculatorUiState>( store.getInitialState())
    val stateFlow = _stateFlow.asStateFlow()
}