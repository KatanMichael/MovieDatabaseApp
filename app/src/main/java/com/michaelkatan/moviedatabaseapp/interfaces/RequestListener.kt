package com.michaelkatan.moviedatabaseapp.interfaces

interface RequestListener
{
    fun <T> onComplete(results : Array<T>)

    fun onError(message : String)

}