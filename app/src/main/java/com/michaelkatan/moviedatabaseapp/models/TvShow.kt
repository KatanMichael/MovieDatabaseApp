package com.michaelkatan.moviedatabaseapp.models

data class TvShow(val id: Int, val poster_path: String, val popularity: Double,
                  val backdrop_path: String, val vote_average: Double,
                   val overview: String, val first_air_date: String, val origin_country: Array<String>,
                  val genre_ids: Array<Int>, val original_language: String, val vote_count: Double,
                  val name: String, val original_name: String)