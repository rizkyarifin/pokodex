package id.rizky.arifin.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonData(
    @field:Json(name = "pokemon")
    val pokemon: Pokemon
)