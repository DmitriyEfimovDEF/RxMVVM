package ru.aisdev.rxjavamvvm.rx.repository

import io.reactivex.Observable
import ru.aisdev.rxjavamvvm.rx.RxApp
import ru.aisdev.rxjavamvvm.rx.model.AppDataBase
import ru.aisdev.rxjavamvvm.rx.model.Repo

object RepoLocalSource:DataSource{
    override fun fetchRepos(username: String): Observable<List<Repo>> {

        return  Observable.fromCallable {
            AppDataBase.getInstance(RxApp.INSTANCE)?.getRepoDao()!!.fetchAllMyStarsRepos()
        }

    }

    override fun saveRepos(repos: List<Repo>) {
        AppDataBase.getInstance(RxApp.INSTANCE)?.getRepoDao()!!.saveAllMyStarRepos(repos)

    }

}
