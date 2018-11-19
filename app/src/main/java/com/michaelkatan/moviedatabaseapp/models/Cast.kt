package com.michaelkatan.moviedatabaseapp.models

data class Cast(val id: Int, val cast_id: Int, val character: String, val gender: Int?,
                val name: String, val profile_path: String?)
