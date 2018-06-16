package eu.mihau.placeholderapisample.api

import eu.mihau.placeholderapisample.model.Comment
import eu.mihau.placeholderapisample.model.Post
import eu.mihau.placeholderapisample.model.User
import eu.mihau.placeholderapisample.utils.provider.scheduler.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class JsonPlaceholderRepository @Inject constructor(private val apiService: APIService, private val schedulerProvider: SchedulerProvider) {

    fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }

    fun getPost(postId : String): Observable<Post> {
        return apiService.getPost(postId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }

    fun getUser(userId : String): Observable<User> {
        return apiService.getUser(userId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }

    fun getComments(postId : String): Observable<List<Comment>> {
        return apiService.getComments(postId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }
}