package com.michaelkatan.moviedatabaseapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.michaelkatan.moviedatabaseapp.R
import com.michaelkatan.moviedatabaseapp.fragments.MainScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.mainFragmentPlace, MainScreenFragment())
                .commit()

    }
}
