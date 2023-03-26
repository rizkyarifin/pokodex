package id.rizky.arifin.core.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<String>,
    val types: List<String>,
    val sprites: List<String>,
    val stats: List<Stat>,
) {

    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getAbilitiesWithPrefix(): List<String> =
        mutableListOf<String>().apply {
            abilities.forEach {
                this.add("\u2022 ${it}")
            }
        }.toList()

    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}


data class Stat(val name: String, val value: String)