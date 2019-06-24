package ru.aisdev.rxjavamvvm.rx.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.aisdev.rxjavamvvm.rx.model.Repo

interface GithubService {

    @GET("users/{user}/starred")
    fun getStarredRepos(@Path("user") username:String): Observable<ArrayList<Repo>>
}