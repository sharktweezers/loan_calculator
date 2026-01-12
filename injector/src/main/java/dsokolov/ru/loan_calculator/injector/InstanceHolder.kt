package dsokolov.ru.loan_calculator.injector

interface InstanceHolder<Instance> {

    fun get(): Instance

    fun release()

    fun isInitialized(): Boolean

}