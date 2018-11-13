package com.michaelkatan.moviedatabaseapp.models

data class TvRequest(val page: Int,val results: Array<TvShow>
                     ,val total_results: Int, val total_pages: Int)