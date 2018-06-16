package eu.mihau.placeholderapisample.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.utils.provider.resource.ResourceProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository, private val resourcesProvider: ResourceProvider) : ViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

    fun test() {
        jsonPlaceholderRepository.getPosts().subscribe({ Log.d(TAG, it.toString())}, {it.printStackTrace()})
        jsonPlaceholderRepository.getPost("1").subscribe({ Log.d(TAG, it.toString())}, {it.printStackTrace()})
        jsonPlaceholderRepository.getUser("1").subscribe({ Log.d(TAG, it.toString())}, {it.printStackTrace()})
        jsonPlaceholderRepository.getComments("1").subscribe({ Log.d(TAG, it.toString())}, {it.printStackTrace()})
    }
}