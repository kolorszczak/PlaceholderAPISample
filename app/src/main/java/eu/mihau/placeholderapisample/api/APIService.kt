package eu.mihau.placeholderapisample.api

import eu.mihau.placeholderapisample.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}