package com.michaelkatan.moviedatabaseapp.models

data class Person(val id: Int, val profile_path: String, val adult: Boolean,
                    val name: String, val popularity: Int)