package dsokolov.ru.loan_calculator.core.format

import dsokolov.ru.loan_calculator.core.ext.EMPTY
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object AmountFormatter {

    private val formatterInt = DecimalFormat(
        "#,###",
        DecimalFormatSymbols.getInstance(Locale.US)
    )

    private val formatterDouble = DecimalFormat(
        "#,##0.00",
        DecimalFormatSymbols.getInstance(Locale.US)
    )

    fun format(amount: Int?, suffix: String? = null): String {
        val value = amount ?: return EMPTY
        val formattedValue = formatterInt.format(value)
        return if (formattedValue.isNotEmpty() && suffix != null) {
            formattedValue + suffix
        } else {
            formattedValue
        }
    }

    fun format(amount: Double?, suffix: String? = null): String {
        val value = amount ?: return EMPTY
        val formattedValue = formatterInt.format(value)
        return if (formattedValue.isNotEmpty() && suffix != null) {
            formattedValue + suffix
        } else {
            formattedValue
        }
    }
}