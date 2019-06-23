package ru.aisdev.rxjavamvvm.rx.model

import com.google.gson.annotations.SerializedName

data class Repo (
    @SerializedName("name")
    val name:String,
    @SerializedName("description")
    val desc: String?,
    @SerializedName("language")
    val lang: String,
    @SerializedName("stargazers_count")
    val stars: Int?

    )