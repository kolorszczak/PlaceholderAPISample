package eu.mihau.placeholderapisample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.mihau.placeholderapisample.view.DescriptionActivity
import eu.mihau.placeholderapisample.view.MainActivity
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDescriptionActivity(): DescriptionActivity
}
