package dsokolov.ru.loan_calculator.presentation

import androidx.lifecycle.viewModelScope
import dsokolov.ru.loan_calculator.mvi.factory.LoanCalculatorStoreFactory
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState
import dsokolov.ru.loan_calculator.mvi_core.BaseMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanCalculatorViewModel @Inject constructor(
    storeFactory: LoanCalculatorStoreFactory,
    stateTransformer: LoanCalculationStateTransformer,
) : BaseMviViewModel() {
    private val _stateFlow = MutableStateFlow<LoanCalculatorState>(LoanCalculatorState.Empty)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        val mviStore = storeFactory.createStore()
        mviStore.start(
            coroutineScope = viewModelScope,
            actionState = { state ->
                viewModelScope.launch(Dispatchers.Main.immediate) {
                    _stateFlow.emit(state)
                }
            },
            actionSideEffect = {/* implements logic for single event */}
        )
    }
}