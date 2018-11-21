package com.michaelkatan.moviedatabaseapp.models

data class MultiSearchResult(val page: Int, val total_results: Int, val total_pages: Int,
                             val results: Array<UnknownItem>)