package ru.aisdev.rxjavamvvm.rx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_my_stars_repos.*
import ru.aisdev.rxjavamvvm.R
import ru.aisdev.rxjavamvvm.rx.adapter.GithubRepoAdapter
import ru.aisdev.rxjavamvvm.rx.viewmodel.RepoViewModel

class MyStarsRepos : AppCompatActivity() {

    lateinit var repoAdapter: GithubRepoAdapter
    private lateinit var repoViewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stars_repos)

        val llm = LinearLayoutManager(applicationContext)
        val divider = DividerItemDecoration(myStarsList.context,DividerItemDecoration.VERTICAL)
        myStarsList.layoutManager = llm

        repoAdapter = GithubRepoAdapter()

        myStarsList.adapter = repoAdapter
        myStarsList.addItemDecoration(divider)

        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)

        getStarredRepos(repoViewModel)
        observeMyStars(repoViewModel)

    }

    private fun getStarredRepos(repoViewModel: RepoViewModel) {
        repoViewModel.getMyStarsRepos("ganzdef")
    }

    private fun observeMyStars(viewModel: RepoViewModel) {
        viewModel.getLiveData().observe(this, Observer {
            repos -> repoAdapter.addRepos(repos)
        })
    }
}
