package ru.aisdev.rxjavamvvm.rx.repository

import io.reactivex.Observable
import ru.aisdev.rxjavamvvm.rx.model.Repo

class Repository(
    private val repoRemoteSource: RepoRemoteSource,
    private val repoLocalSource: RepoLocalSource
):DataSource {
    override fun fetchRepos(username: String): Observable<List<Repo>> {

        return Observable.concatArray(repoLocalSource.fetchRepos(username),repoRemoteSource.fetchRepos(username))
            .doOnNext { repos -> saveRepos(repos) }
            .onErrorResumeNext(Observable.empty()) //handle the error of no internet connection
    }

    override fun saveRepos(repos: List<Repo>) {
            repoLocalSource.saveRepos(repos)

    }
}