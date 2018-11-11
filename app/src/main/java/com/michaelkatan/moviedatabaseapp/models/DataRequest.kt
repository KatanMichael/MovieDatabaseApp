package com.michaelkatan.moviedatabaseapp.models

data class MovieRequest (val page: Int, val total_results: Int
                         , val total_pages: Int, val results: Array<Movie>)

data class TvRequest(val page: Int,val results: Array<TvShow>
                     ,val total_results: Int, val total_pages: Int)