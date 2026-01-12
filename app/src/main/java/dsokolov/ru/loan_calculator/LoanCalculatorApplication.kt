package dsokolov.ru.loan_calculator

import android.app.Application
import dsokolov.ru.loan_calculator.di.AppDeps
import dsokolov.ru.loan_calculator.di.Di

class LoanCalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        val appDependencies = object : AppDeps {
            override val application: LoanCalculatorApplication
                get() = this@LoanCalculatorApplication

        }
        Di.init(appDependencies)
    }
}