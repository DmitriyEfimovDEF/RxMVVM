package ru.aisdev.rxjavamvvm.rx.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.stars_item.view.*
import ru.aisdev.rxjavamvvm.R
import ru.aisdev.rxjavamvvm.rx.model.Repo

class GithubRepoAdapter: RecyclerView.Adapter<GithubRepoAdapter.StarRepoViewHolder>() {

    private val data = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stars_item,parent,false)
        return StarRepoViewHolder(view)

    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StarRepoViewHolder, position: Int) {
        holder.repoName.text = data[position].name
        holder.repoDesc.text = data[position].desc
        holder.repoLang.text = data[position].lang
        holder.repoCount.text = data[position].stars.toString()
        data[position].desc?.let {
            holder.repoDesc.text = data[position].desc
        }?:run {
            holder.repoDesc.text = " no description available"
        }

    }

    class StarRepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoName = view.repoName
        val repoDesc = view.desc
        val repoLang = view.lang
        val repoCount = view.starsCount
    }

    fun addRepos(repos: List<Repo>) {
        data.clear()
        data.addAll(repos)
        notifyDataSetChanged()
    }
}