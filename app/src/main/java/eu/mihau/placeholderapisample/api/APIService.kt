package eu.mihau.placeholderapisample.api

import eu.mihau.placeholderapisample.model.Comment
import eu.mihau.placeholderapisample.model.Post
import eu.mihau.placeholderapisample.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts/{postId}")
    fun getPost(@Path("postId") postId : String): Observable<Post>

    @GET("/posts/{postId}/comments")
    fun getComments(@Path("postId") postId : String): Observable<List<Comment>>

    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId : String): Observable<User>
}