package dsokolov.ru.loan_calculator.mvi.handler

import dsokolov.ru.loan_calculator.mvi_core.DefaultCommandHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import dsokolov.ru.loan_calculator.mvi.command.LoanCalculatorCommand as Command
import dsokolov.ru.loan_calculator.mvi.event.LoanCalculatorEvent as Event

class LoanCalculatorCommandHandler(
) : DefaultCommandHandler<Event, Command>() {
    override fun handleCommand(command: Command): Flow<Event> {
        return emptyFlow()
    }
}