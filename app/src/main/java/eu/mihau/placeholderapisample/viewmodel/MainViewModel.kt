package eu.mihau.placeholderapisample.viewmodel

import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.model.Post
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) : BaseViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

    val mainViewModelSubject: PublishSubject<MainViewModelEvent> = PublishSubject.create()

    fun getPosts() {
        jsonPlaceholderRepository.getPosts()
                .subscribe({ mainViewModelSubject.onNext(MainViewModelEvent(MainViewModelEvent.Type.DATA_LOADED, it)) }, ::error)
                .bind(this)
    }

    private fun error(error: Throwable) = mainViewModelSubject.onNext(MainViewModelEvent(MainViewModelEvent.Type.ERROR, error = error))
}

data class MainViewModelEvent(val type: Type,
                              val postList: List<Post> = emptyList(),
                              val error: Throwable = Throwable()) {
    enum class Type {
        DATA_LOADED, ERROR
    }
}