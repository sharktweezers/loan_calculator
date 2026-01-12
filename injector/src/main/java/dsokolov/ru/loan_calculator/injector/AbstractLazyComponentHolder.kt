package dsokolov.ru.loan_calculator.injector

abstract class AbstractLazyComponentHolder<Api : ComponentApi, Dependencies : ComponentDependencies> :
    AbstractLazyInstanceHolder<Api, Dependencies>(), ComponentHolder<Api>