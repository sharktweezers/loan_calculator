package dsokolov.ru.loan_calculator.di

import dsokolov.ru.loan_calculator.LoanCalculatorApplication
import dsokolov.ru.loan_calculator.injector.ComponentDependencies

interface AppDeps : ComponentDependencies {
    val application: LoanCalculatorApplication
}