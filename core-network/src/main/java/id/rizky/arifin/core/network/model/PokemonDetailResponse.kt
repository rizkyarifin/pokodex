package id.rizky.arifin.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @field:Json(name = "abilities") val abilities: List<Abilities>,
    @field:Json(name = "base_experience") val baseExperience: Int,
    @field:Json(name = "forms") val forms: List<Forms>,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "types") val types: List<Types>,
    @field:Json(name = "sprites") val sprites: Sprites,
    @field:Json(name = "stats") var stats: List<Stats>,
    @field:Json(name = "weight") val weight: Int,
) {
    @JsonClass(generateAdapter = true)
    data class Abilities(
        @field:Json(name = "ability") var ability: Ability,
        @field:Json(name = "is_hidden") var isHidden: Boolean,
        @field:Json(name = "slot") var slot: Int
    )

    @JsonClass(generateAdapter = true)
    data class Ability(
        @field:Json(name = "name") var name: String,
        @field:Json(name = "url") var url: String
    )

    @JsonClass(generateAdapter = true)
    data class Forms(
        @field:Json(name = "name") var name: String,
        @field:Json(name = "url") var url: String
    )

    @JsonClass(generateAdapter = true)
    data class Type(
        @field:Json(name = "name") var name: String,
        @field:Json(name = "url") var url: String
    )

    @JsonClass(generateAdapter = true)
    data class Types(
        @field:Json(name = "slot") var slot: Int,
        @field:Json(name = "type") var type: Type
    )

    @JsonClass(generateAdapter = true)
    data class Sprites(
        @field:Json(name = "back_default") var backDefault: String? = null,
        @field:Json(name = "back_female") var backFemale: String? = null,
        @field:Json(name = "back_shiny") var backShiny: String? = null,
        @field:Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @field:Json(name = "front_default") var frontDefault: String? = null,
        @field:Json(name = "front_female") var frontFemale: String? = null,
        @field:Json(name = "front_shiny") var frontShiny: String? = null,
        @field:Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Stats(
        @field:Json(name = "base_stat") var baseStat: Int? = null,
        @field:Json(name = "effort") var effort: Int? = null,
        @field:Json(name = "stat") var stat: Stat? = Stat()

    )

    @JsonClass(generateAdapter = true)
    data class Stat(
        @field:Json(name = "name") var name: String? = null,
        @field:Json(name = "url") var url: String? = null
    )
}
