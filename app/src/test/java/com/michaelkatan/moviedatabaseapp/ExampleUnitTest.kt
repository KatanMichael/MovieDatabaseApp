package com.michaelkatan.moviedatabaseapp

import android.app.Person
import android.util.Log
import com.michaelkatan.moviedatabaseapp.controllers.RetroController
import com.michaelkatan.moviedatabaseapp.interfaces.RequestListener
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 *
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect()
    {
        val controller = RetroController

        controller.getPopularPersons(object : RequestListener
        {
            override fun <T> onComplete(results: Array<T>)
            {
                val tempArray = results as Array<Person>

                for(p in tempArray)
                {
                    Log.d("Testing",p.toString())
                }

            }

            override fun onError(message: String)
            {
                Log.d("Testing","Error: $message")
            }

        })
    }
}
