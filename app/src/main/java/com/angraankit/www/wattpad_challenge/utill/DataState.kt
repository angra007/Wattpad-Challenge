package com.angraankit.www.wattpad_challenge.utill

import java.lang.Exception

sealed class DataState <out R>{
    data class Success <out T> (val data : T) : DataState <T>()
    data class Error (val exception : Exception) : DataState <Nothing> ()
    data class Loading <out T> (val data : T?) : DataState <T>()
}