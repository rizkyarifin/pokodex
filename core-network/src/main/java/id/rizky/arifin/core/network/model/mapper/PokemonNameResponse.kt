package id.rizky.arifin.core.network.model.mapper

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.rizky.arifin.core.model.PokemonData

@JsonClass(generateAdapter = true)
data class PokemonNameResponse(
    @field:Json(name = "pokemon")
    val results: List<PokemonData>
)