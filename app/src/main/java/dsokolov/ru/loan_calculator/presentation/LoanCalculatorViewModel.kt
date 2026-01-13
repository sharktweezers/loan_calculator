package dsokolov.ru.loan_calculator.presentation

import androidx.lifecycle.viewModelScope
import dsokolov.ru.loan_calculator.core.ext.throttleFirst
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent
import dsokolov.ru.loan_calculator.mvi.factory.LoanCalculatorStoreFactory
import dsokolov.ru.loan_calculator.mvi_core.BaseMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanCalculatorViewModel @Inject constructor(
    storeFactory: LoanCalculatorStoreFactory,
    stateTransformer: LoanCalculatorStateTransformer,
) : BaseMviViewModel() {
    private val _stateFlow = MutableStateFlow<LoanCalculatorUiState>(
        LoanCalculatorUiState.Empty
    )
    val stateFlow = _stateFlow.asStateFlow()

    private val uiEvent = MutableSharedFlow<LoanCalculatorEvent>()

    private val uiThrottledEvent = MutableSharedFlow<LoanCalculatorEvent>()

    init {
        val mviStore = storeFactory.createStore()
        mviStore.start(
            coroutineScope = viewModelScope,
            actionState = { state ->
                viewModelScope.launch(Dispatchers.Main.immediate) {
                    _stateFlow.emit(stateTransformer.transform(state))
                }
            },
            actionSideEffect = {/* implements logic for single event */},
        )

        val uiEvents = merge(
            uiEvent,
            uiThrottledEvent.throttleFirst(),
        )

        uiEvents.onEach(mviStore::onEvent)
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    fun onAmountSliderChanged(value: Float) = launchUnit {

    }

    fun onDaysPeriodSliderChanged(value: Float) = launchUnit {

    }
}