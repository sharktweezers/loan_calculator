package dsokolov.ru.loan_calculator.core.string_provider

interface StringProvider {
    fun getString(id: Int): String

    fun getString(
        id: Int,
        vararg args: Any,
    ): String

    fun getText(id: Int): CharSequence
}