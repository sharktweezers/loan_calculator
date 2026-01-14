package dsokolov.ru.loan_calculator.remote.preferences

import android.content.Context
import com.google.gson.Gson
import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorPrefer
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorMapper.Companion.MIN_RANGE_AMOUNT
import dsokolov.ru.loan_calculator.core.mapper.LoanCalculatorMapper.Companion.MIN_RANGE_DAYS_PERIOD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesImpl @Inject internal constructor(
    appContext: Context
) : SharedPreferences {
    private val prefs = appContext.getSharedPreferences(
        PREFERENCE_FILE_PATH_AND_NAME,
        Context.MODE_PRIVATE,
    )

    private val editor = prefs.edit()

    override suspend fun putLoanCalculatorPrefer(loanCalculatorPrefer: LoanCalculatorPrefer) {
        val gson = Gson()
        val jsonString = gson.toJson(loanCalculatorPrefer)
        editor.putString(KEY_LOAN_CALCULATOR_PREFER, jsonString).commit()
    }

    override suspend fun getLoanCalculatorPrefer(): LoanCalculatorPrefer {
        prefs.getString(KEY_LOAN_CALCULATOR_PREFER, null)?.let {
            val gson = Gson()
            return gson.fromJson(it, LoanCalculatorPrefer::class.java)
        }

        return LoanCalculatorPrefer(MIN_RANGE_AMOUNT, MIN_RANGE_DAYS_PERIOD)
    }

    private companion object {
        const val PREFERENCE_FILE_PATH = "dsokolov.ru.loan_calculator."
        const val PREFERENCE_FILE_NAME = "PREFERENCE_FILE_KEY"
        const val PREFERENCE_FILE_PATH_AND_NAME = "$PREFERENCE_FILE_PATH$PREFERENCE_FILE_NAME"
        const val KEY_LOAN_CALCULATOR_PREFER = "KEY_LOAN_CALCULATOR_PREFER"
    }
}