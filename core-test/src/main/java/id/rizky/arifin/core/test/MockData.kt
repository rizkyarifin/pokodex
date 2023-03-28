package id.rizky.arifin.core.test

import id.rizky.arifin.core.model.Pokemon

object MockData {
    fun mockPokemonList() = listOf(mockPokemon())

    fun mockPokemon() = Pokemon(
        1281, 0, "bulbasaur",
        "https://pokeapi.co/api/v2/pokemon/1/"
    )
}