package ru.aisdev.rxjavamvvm.rx.repository

import io.reactivex.Observable
import ru.aisdev.rxjavamvvm.rx.model.Repo


interface DataSource {
    fun fetchRepos(username:String): Observable<List<Repo>>
    fun saveRepos(repos: List<Repo>)
}