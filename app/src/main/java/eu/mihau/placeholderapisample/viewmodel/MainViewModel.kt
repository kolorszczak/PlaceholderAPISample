package eu.mihau.placeholderapisample.viewmodel

import android.arch.lifecycle.ViewModel
import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.utils.provider.resource.ResourceProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository, private val resourcesProvider: ResourceProvider) : ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

}