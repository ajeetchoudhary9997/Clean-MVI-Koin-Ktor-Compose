package com.example.composedemo.utils

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val errorMsg: String,val code:Int) : Resource<Nothing>()
}