package ru.aisdev.rxjavamvvm.rx.repository

import io.reactivex.Observable
import ru.aisdev.rxjavamvvm.rx.model.Repo
import ru.aisdev.rxjavamvvm.rx.network.GithubApiClient

object RepoRemoteSource : DataSource {
    override fun fetchRepos(username: String): Observable<List<Repo>> {
        return GithubApiClient.getGithubSerivce().getStarredRepos(username)
    }

    override fun saveRepos(repos: List<Repo>) {


    }

}
