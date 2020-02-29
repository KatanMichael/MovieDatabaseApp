package com.michaelkatan.moviedatabaseapp.models

open class PopularItem(var popId: Int, var PosterPath: String?, var type: String)
{

    constructor(movie: Movie) : this(movie.id, movie.poster_path, "movie")

    constructor(tvShow: TvShow):this(tvShow.id,tvShow.poster_path,"tvShow")




}