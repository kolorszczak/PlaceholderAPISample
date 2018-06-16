package eu.mihau.placeholderapisample

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import eu.mihau.placeholderapisample.di.component.DaggerAppComponent
import eu.mihau.placeholderapisample.di.module.AppModule
import eu.mihau.placeholderapisample.di.module.RestModule

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun applicationInjector(): AndroidInjector< out DaggerApplication> = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .restModule(RestModule())
            .application(this)
            .build()
}