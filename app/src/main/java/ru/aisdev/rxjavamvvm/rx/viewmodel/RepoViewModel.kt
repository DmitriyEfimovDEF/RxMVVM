package ru.aisdev.rxjavamvvm.rx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.aisdev.rxjavamvvm.rx.model.Repo
import ru.aisdev.rxjavamvvm.rx.repository.RepoLocalSource
import ru.aisdev.rxjavamvvm.rx.repository.RepoRemoteSource
import ru.aisdev.rxjavamvvm.rx.repository.Repository

class RepoViewModel: ViewModel() {

    private val repoLiveData = MutableLiveData<List<Repo>>()
    private val compoDisposable = CompositeDisposable()
    private val repository = Repository(RepoRemoteSource,RepoLocalSource)

    override fun onCleared() {
        super.onCleared()
        compoDisposable.clear()
    }

    fun getMyStarsRepos(userName: String){
        if (repoLiveData.value != null) {
            return
        }
       val reposDisposable = repository.fetchRepos(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                repoLiveData.value = it
            }

        compoDisposable.add(reposDisposable)
    }

    fun getLiveData(): LiveData<List<Repo>> = repoLiveData

}