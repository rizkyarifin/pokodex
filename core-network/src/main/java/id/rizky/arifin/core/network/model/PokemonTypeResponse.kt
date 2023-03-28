package id.rizky.arifin.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.rizky.arifin.core.model.Pokemon

@JsonClass(generateAdapter = true)
data class PokemonTypeResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    @field:Json(name = "results") val results: List<Pokemon>
)

