package dsokolov.ru.loan_calculator.injector

abstract class AbstractComponentHolder<Api : ComponentApi, Dependencies : ComponentDependencies> :
    AbstractInstanceHolder<Api, Dependencies>(), ComponentHolder<Api>