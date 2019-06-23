package ru.aisdev.rxjavamvvm.rx

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_my_stars_repos.*
import ru.aisdev.rxjavamvvm.R
import ru.aisdev.rxjavamvvm.rx.adapter.GithubRepoAdapter
import ru.aisdev.rxjavamvvm.rx.network.GithubApiClient

class MyStarsRepos : AppCompatActivity() {

    lateinit var repoAdapter: GithubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        val llm = LinearLayoutManager(applicationContext)
        val divider = DividerItemDecoration(myStarsList.context,DividerItemDecoration.VERTICAL)
        myStarsList.layoutManager = llm

        repoAdapter = GithubRepoAdapter()

        myStarsList.adapter = repoAdapter
        myStarsList.addItemDecoration(divider)

        getStarredRepos()
    }

    @SuppressLint("CheckResult")
    private fun getStarredRepos() {
        GithubApiClient.getGithubSerivce().getStarredRepos("ganzdef")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
               repoAdapter.addRepos(it)
            }
    }
}
