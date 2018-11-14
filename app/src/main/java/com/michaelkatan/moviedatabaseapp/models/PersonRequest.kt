package com.michaelkatan.moviedatabaseapp.models

class PersonRequest(val total_results: Int, val total_pages: Int,
                         val results: Array<Person>)