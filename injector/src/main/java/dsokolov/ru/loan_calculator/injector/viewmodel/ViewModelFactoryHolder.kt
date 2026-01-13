package dsokolov.ru.loan_calculator.injector.viewmodel

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

object ViewModelFactoryHolder {
    val store = ViewModelFactoryStore()
}

class ViewModelFactoryStore internal constructor() {
    lateinit var viewModelFactory: ViewModelProvider.Factory
        private set

    @Inject
    fun onInject(factory: ViewModelProvider.Factory) {
        viewModelFactory = factory
    }
}