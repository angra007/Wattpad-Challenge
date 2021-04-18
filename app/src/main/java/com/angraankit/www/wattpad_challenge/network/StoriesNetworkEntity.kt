package com.angraankit.www.wattpad_challenge.network

import com.google.gson.annotations.SerializedName


data class StorieNetworkEntity (
    @SerializedName("id")
    val id : String?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("user")
    val user : UserNetworkEntity?,

    @SerializedName("cover")
    val cover : String?
)


data class StoriesNetworkEntity (
    @SerializedName("stories")
    val stories : List<StorieNetworkEntity>?
)

data class UserNetworkEntity (
    @SerializedName("name")
    val name : String?,

    @SerializedName("avatar")
    val avatar : String?,

    @SerializedName("fullname")
    val fullname : String?
)