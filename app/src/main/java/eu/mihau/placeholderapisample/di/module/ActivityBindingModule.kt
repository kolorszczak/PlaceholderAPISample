package eu.mihau.placeholderapisample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.mihau.placeholderapisample.view.DescriptionActivity
import eu.mihau.placeholderapisample.view.MainActivity

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindDescriptionActivity(): DescriptionActivity
}
