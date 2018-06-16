package eu.mihau.placeholderapisample.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.utils.provider.resource.AppResourcesProvider
import eu.mihau.placeholderapisample.utils.provider.resource.ResourceProvider
import eu.mihau.placeholderapisample.utils.provider.scheduler.AppSchedulerProvider
import eu.mihau.placeholderapisample.utils.provider.scheduler.SchedulerProvider
import eu.mihau.placeholderapisample.viewmodel.ViewModelFactory
import javax.inject.Singleton

@Module
class AppModule(var app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(resourceProvider: ResourceProvider, schedulerProvider: SchedulerProvider, jsonPlaceholderRepository: JsonPlaceholderRepository): ViewModelFactory {
        return ViewModelFactory(resourceProvider, jsonPlaceholderRepository, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideResourceProvider(): ResourceProvider {
        return AppResourcesProvider(app)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}