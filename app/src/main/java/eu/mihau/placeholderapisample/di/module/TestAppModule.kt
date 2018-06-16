package eu.mihau.placeholderapisample.di.module

import dagger.Module
import dagger.Provides
import eu.mihau.placeholderapisample.utils.provider.resource.ResourceProvider
import eu.mihau.placeholderapisample.utils.provider.resource.TestResourceProvider
import eu.mihau.placeholderapisample.utils.provider.scheduler.SchedulerProvider
import eu.mihau.placeholderapisample.utils.provider.scheduler.TestSchedulerProvider
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun provideResourceProvider(): ResourceProvider {
        return TestResourceProvider()
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return TestSchedulerProvider()
    }
}
