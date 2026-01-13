package dsokolov.ru.loan_calculator.core.string_provider

import android.content.Context
import dsokolov.ru.loan_calculator.core.ext.EMPTY
import java.lang.ref.WeakReference
import javax.inject.Inject

class StringProviderImpl @Inject internal constructor(appContext: Context): StringProvider {
    val weakContext = WeakReference(appContext)

    override fun getString(id: Int): String {
        return weakContext.get()?.getString(id).orEmpty()
    }

    override fun getString(
        id: Int,
        vararg args: Any,
    ): String = weakContext.get()?.getString(id, *args).orEmpty()

    override fun getText(id: Int): CharSequence = weakContext.get()?.getText(id) ?: EMPTY
}