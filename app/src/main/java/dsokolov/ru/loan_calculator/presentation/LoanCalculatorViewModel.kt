package dsokolov.ru.loan_calculator.presentation

import androidx.lifecycle.viewModelScope
import dsokolov.ru.loan_calculator.core.ext.throttleFirst
import dsokolov.ru.loan_calculator.core.ext.throttleLatest
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent
import dsokolov.ru.loan_calculator.mvi.factory.LoanCalculatorStoreFactory
import dsokolov.ru.loan_calculator.mvi.side_effect.LoanCalculatorUiSideEffectMapper
import dsokolov.ru.loan_calculator.mvi_core.BaseMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class LoanCalculatorViewModel @Inject constructor(
    storeFactory: LoanCalculatorStoreFactory,
    stateTransformer: LoanCalculatorStateTransformer,
    loanCalculatorUiSideEffectMapper: LoanCalculatorUiSideEffectMapper,
) : BaseMviViewModel() {
    private val _stateFlow = MutableStateFlow<LoanCalculatorUiState>(
        LoanCalculatorUiState.Empty
    )
    val stateFlow = _stateFlow.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<LoanCalculatorUiSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    private val uiEvent = MutableSharedFlow<LoanCalculatorEvent>()
    private val uiThrottledFirstEvent = MutableSharedFlow<LoanCalculatorEvent>()
    private val uiThrottledLatestEvent = MutableSharedFlow<LoanCalculatorEvent>()

    init {
        val mviStore = storeFactory.createStore()
        mviStore.start(
            coroutineScope = viewModelScope,
            actionState = { state ->
                viewModelScope.launch(Dispatchers.Main.immediate) {
                    _stateFlow.emit(stateTransformer.transform(state))
                }
            },
            actionSideEffect = { mviSideEffect ->
                val uiSideEffect = loanCalculatorUiSideEffectMapper.map(mviSideEffect = mviSideEffect)
                _sideEffectFlow.emit(uiSideEffect)
            },
        )

        val uiEvents = merge(
            uiEvent,
            uiThrottledFirstEvent.throttleFirst(),
            uiThrottledLatestEvent.throttleLatest(250L)
        )

        uiEvents.onEach(mviStore::onEvent)
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    fun onAmountSliderChanged(value: Float) = launchUnit {
        uiThrottledLatestEvent.emit(LoanCalculatorEvent.LoanCalculatorEventUi.AmountChanged(value))
    }

    fun onDaysPeriodSliderChanged(value: Float) = launchUnit {
        uiEvent.emit(LoanCalculatorEvent.LoanCalculatorEventUi.PeriodChanged(value))
    }

    fun onApplyClick() = launchUnit {
        uiThrottledLatestEvent.emit(LoanCalculatorEvent.LoanCalculatorEventUi.Apply)
    }
}