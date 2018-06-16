package eu.mihau.placeholderapisample.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.utils.provider.resource.ResourceProvider
import eu.mihau.placeholderapisample.utils.provider.scheduler.SchedulerProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
        var resourcesProvider: ResourceProvider,
        var repository: JsonPlaceholderRepository,
        var schedulerProvider: SchedulerProvider) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw RuntimeException("Unknown viewModel class")
    }

    operator fun <T : ViewModel> get(modelClass: Class<T>, activity: FragmentActivity): T {
        return ViewModelProviders.of(activity, this).get(modelClass)
    }

}