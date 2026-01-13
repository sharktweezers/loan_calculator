package dsokolov.ru.loan_calculator.di

import dsokolov.ru.loan_calculator.di.AppComponent
import dsokolov.ru.loan_calculator.injector.AbstractComponentHolder
import javax.inject.Inject

object Di : AbstractComponentHolder<AppApi, AppDeps>() {

    override fun initialize(dependencies: AppDeps): AppApi {
        return DaggerAppComponent
            .builder()
            .appDeps(dependencies)
            .build()
    }

    override fun onInitialized(component: AppApi, dependencies: AppDeps) {
        getComponent().inject(ComponentManager())
    }

    internal fun getComponent(): AppComponent = get() as AppComponent

    class ComponentManager {

        @Inject
        fun init(
            // add your features component holders
        ) {
            //This fores the component holders to initiate within their dagger modules
        }

    }

}