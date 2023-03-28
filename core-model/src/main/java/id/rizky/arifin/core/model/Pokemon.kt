package id.rizky.arifin.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    var totalData: Int = 0,
    var page: Int = 0,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url") val url: String
) {

    fun getImageUrl(): String {
        val index = getId()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

    fun getPokemonTypes(): List<String> = listOf("grass", "fighting")

    private fun getId(): String = url.split("/".toRegex()).dropLast(1).last()

    fun getIdString(): String = String.format("#%03d", getId().toInt())
}
