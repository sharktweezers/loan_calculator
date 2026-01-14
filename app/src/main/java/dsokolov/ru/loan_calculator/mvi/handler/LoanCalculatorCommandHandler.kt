package dsokolov.ru.loan_calculator.mvi.handler

import dsokolov.ru.loan_calculator.core.use_cases.ApplyLoanCalculatorUseCase
import dsokolov.ru.loan_calculator.core.use_cases.GetInitLoanCalculatorUseCase
import dsokolov.ru.loan_calculator.core.use_cases.SaveLoanCalculatorPreferUseCase
import dsokolov.ru.loan_calculator.mvi_core.DefaultCommandHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import dsokolov.ru.loan_calculator.mvi.command.LoanCalculatorCommand as Command
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent.LoanCalculatorEventDomain as Event

@OptIn(ExperimentalCoroutinesApi::class)
class LoanCalculatorCommandHandler(
    private val saveLoanCalculatorPreferUseCase: SaveLoanCalculatorPreferUseCase,
    private val getInitLoanCalculatorUseCase: GetInitLoanCalculatorUseCase,
    private val applyLoanCalculatorUseCase: ApplyLoanCalculatorUseCase,
) : DefaultCommandHandler<Event, Command>() {
    override fun handleCommand(command: Command): Flow<Event> {
        return when (command) {
            is Command.GetInitFromPreferences -> getInitFromPreferences()
            is Command.UpdateLoanCalculatorPreference -> updateLoanCalculatorPreference(command)
            is Command.Apply -> apply(command)
        }
    }

    private fun getInitFromPreferences(): Flow<Event> {
        return flow {
            emit(getInitLoanCalculatorUseCase())
        }
            .flowOn(Dispatchers.IO)
            .map(Event::ReceivedLoanCalculatorState)
    }

    private fun updateLoanCalculatorPreference(
        command: Command.UpdateLoanCalculatorPreference
    ): Flow<Event> {
        return flow {
            emit(
                saveLoanCalculatorPreferUseCase.invoke(
                    amount = command.amount,
                    period = command.period,
                )
            )
        }
            .flowOn(Dispatchers.IO)
            .map(Event::ReceivedLoanCalculatorState)
    }

    private fun apply(
        command: Command.Apply
    ): Flow<Event> {
        return flow {
            delay(2000L) // Имитация отправки транзакции
            emit(
                applyLoanCalculatorUseCase.invoke(
                    amount = command.amount,
                    period = command.period,
                    totalRepayment = command.totalRepayment
                )
            )
        }
            .flowOn(Dispatchers.IO)
            .flatMapLatest {
                it
            }
            .map {
                Event.ReceiveLoanCalculatorTransaction(it)
            }
    }
}