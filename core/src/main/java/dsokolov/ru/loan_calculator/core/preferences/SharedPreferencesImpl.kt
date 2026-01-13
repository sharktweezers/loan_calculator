package dsokolov.ru.loan_calculator.core.preferences

import android.content.Context
import com.google.gson.Gson
import dsokolov.ru.loan_calculator.core.domain.models.LoanCalculatorPrefer
import dsokolov.ru.loan_calculator.core.use_cases.GetInitLoanCalculatorUseCase.Companion.MIN_RANGE_AMOUNT
import javax.inject.Inject

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
        editor.putString(KEY_LOAN_CALCULATOR_PREFER, jsonString)
    }

    override suspend fun getLoanCalculatorPrefer(): LoanCalculatorPrefer {
        prefs.getString(KEY_LOAN_CALCULATOR_PREFER, null)?.let {
            val gson = Gson()
            return gson.fromJson(it, LoanCalculatorPrefer::class.java)
        }

        return LoanCalculatorPrefer(MIN_RANGE_AMOUNT, 0)
    }

    private companion object {
        const val PREFERENCE_FILE_PATH = "dsokolov.ru.loan_calculator."
        const val PREFERENCE_FILE_NAME = "PREFERENCE_FILE_KEY"
        const val PREFERENCE_FILE_PATH_AND_NAME = "$PREFERENCE_FILE_PATH$PREFERENCE_FILE_NAME"
        const val KEY_LOAN_CALCULATOR_PREFER = "KEY_LOAN_CALCULATOR_PREFER"
    }
}