package ru.aisdev.rxjavamvvm.rx.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.aisdev.rxjavamvvm.rx.model.Repo
import ru.aisdev.rxjavamvvm.rx.network.GithubApiClient

class RepoViewModel: ViewModel() {

    val repoLiveData = MutableLiveData<ArrayList<Repo>>()
    val compoDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()

        compoDisposable.clear()
    }

    fun getMyStarsRepos(userName: String){

       val reposDisposable = GithubApiClient.getGithubSerivce().getStarredRepos("ganzdef")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                repoLiveData.value = it
            }

        compoDisposable.add(reposDisposable)
    }
    fun getLiveData(): LiveData<ArrayList<Repo>> = repoLiveData

}