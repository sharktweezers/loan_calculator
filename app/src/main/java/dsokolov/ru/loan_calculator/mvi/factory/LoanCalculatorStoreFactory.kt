package dsokolov.ru.loan_calculator.mvi.factory

import dsokolov.ru.loan_calculator.mvi.handler.LoanCalculatorCommandHandler
import dsokolov.ru.loan_calculator.mvi.reducer.LoanCalculatorReducer
import dsokolov.ru.loan_calculator.mvi_core.Store
import dsokolov.ru.loan_calculator.mvi_core.StoreFactory
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent as Event
import dsokolov.ru.loan_calculator.mvi.side_effect.LoanCalculatorSideEffect as SideEffect
import dsokolov.ru.loan_calculator.mvi.state.LoanCalculatorState as State

class LoanCalculatorStoreFactory(
    private val reducer: LoanCalculatorReducer,
    private val commandHandler: LoanCalculatorCommandHandler,
) {
    fun createStore(): Store<Event, State, SideEffect> {
        return StoreFactory.createStore(
            stateUpdater = reducer,
            initialState = reducer.getInitialState(),
            initialCommands = reducer.getInitialCommand(),
            commandHandler = commandHandler,
        )
    }
}