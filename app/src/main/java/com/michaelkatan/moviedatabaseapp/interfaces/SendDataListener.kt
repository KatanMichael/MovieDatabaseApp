package com.michaelkatan.moviedatabaseapp.interfaces

interface SendDataListener
{
    fun <T> onDataReceived(data: T, type: String)

}