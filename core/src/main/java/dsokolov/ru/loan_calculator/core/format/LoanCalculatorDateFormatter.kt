package dsokolov.ru.loan_calculator.core.format

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LoanCalculatorDateFormatter {
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    fun format(plusDays: Long): String {
        val date = LocalDate.now().plusDays(plusDays)
        return date.format(dateFormatter)
    }
}