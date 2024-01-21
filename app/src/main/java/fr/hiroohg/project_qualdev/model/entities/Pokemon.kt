package fr.hiroohg.project_qualdev.model.entities

import com.squareup.moshi.Json

// class where props are bind to json fields with @Json from moshi
data class Pokemon(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "type") val types: List<String>,
    @Json(name = "image_url") val image: String,
    @Json(name = "evolutions") val evolutions: Evolutions,
)

// an object in an object
data class Evolutions(
    @Json(name = "before") val before: List<Int>,
    @Json(name = "after") val after: List<Int>,
)

data class PokeApiEntity(
    @Json(name = "id") val id: Int
)