package eu.mihau.placeholderapisample.viewmodel

import android.databinding.ObservableField
import eu.mihau.placeholderapisample.api.JsonPlaceholderRepository
import eu.mihau.placeholderapisample.model.Comment
import eu.mihau.placeholderapisample.model.Post
import eu.mihau.placeholderapisample.model.User
import io.reactivex.subjects.PublishSubject
import java.util.*
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(private val jsonPlaceholderRepository: JsonPlaceholderRepository) : BaseViewModel() {
    private val TAG = DescriptionViewModel::class.java.simpleName

    val user: ObservableField<User> = ObservableField()
    val post: ObservableField<Post> = ObservableField()
    val comments: ArrayList<Comment> = ArrayList()

    val descriptionViewModelSubject = PublishSubject.create<DescriptionViewModelEvent>()

    fun getUser() {
        post.get()?.let {
            jsonPlaceholderRepository.getUser(it.userId.toString()).subscribe({ user.set(it) }, ::error)
                    .bind(this)
        }
    }

    fun getComments() {
        post.get()?.let {
            jsonPlaceholderRepository.getComments(it.id.toString()).subscribe({
                comments.addAll(it)
                descriptionViewModelSubject.onNext(DescriptionViewModelEvent(DescriptionViewModelEvent.Type.COMMENTS_LOADED))
            }, ::error)
                    .bind(this)
        }
    }

    private fun error(error: Throwable) = descriptionViewModelSubject.onNext(DescriptionViewModelEvent(DescriptionViewModelEvent.Type.ERROR, error))
}

data class DescriptionViewModelEvent(val type: Type,
                                     val error: Throwable = Throwable()) {
    enum class Type {
        COMMENTS_LOADED, ERROR
    }
}