package com.michaelkatan.moviedatabaseapp.models

data class PopularItem(var id: Int, var PosterPath: String, var type: String)
{
    fun convertToPopularItem(movie: Movie)
    {
        this.id = movie.id
        this.PosterPath = movie.poster_path
        this.type = "movie"
    }

    fun convertToPopularItem(tvShow: TvShow)
    {
        this.id = tvShow.id
        this.PosterPath = tvShow.poster_path
        this.type = "movie"
    }

}