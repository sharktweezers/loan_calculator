package dsokolov.ru.loan_calculator.core

import android.content.Context
import java.lang.ref.WeakReference

class StringProvider(appContext: Context) {
    val weakContext = WeakReference<Context>(appContext)

    fun getString(id: Int): String {
        return weakContext.get()?.getString(id).orEmpty()
    }

    fun getString(
        id: Int,
        vararg args: Any,
    ): String = weakContext.get()?.getString(id, *args).orEmpty()

    fun getText(id: Int): CharSequence = weakContext.get()?.getText(id) ?: ""
}