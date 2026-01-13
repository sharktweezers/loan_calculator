package dsokolov.ru.loan_calculator.core.domain.models

import java.io.Serializable

class LoanCalculatorPrefer(
    val amount: Int,
    val daysPeriod: Int,
) : Serializable