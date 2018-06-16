package eu.mihau.placeholderapisample.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import eu.mihau.placeholderapisample.MainApplication
import eu.mihau.placeholderapisample.di.module.ActivityBindingModule
import eu.mihau.placeholderapisample.di.module.AppModule
import eu.mihau.placeholderapisample.di.module.RestModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, RestModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<MainApplication> {

    override fun inject(app: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun restModule(restModule: RestModule): Builder

        fun build(): AppComponent

    }
}