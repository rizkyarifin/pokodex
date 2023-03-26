package id.rizky.arifin.core.network.service

import com.skydoves.sandwich.ApiResponse
import id.rizky.arifin.core.network.model.mapper.PokemonDetailResponse
import id.rizky.arifin.core.network.model.mapper.PokemonResponse
import javax.inject.Inject

class PokedexClient @Inject constructor(
    private val pokedexService: PokedexService
) {

    suspend fun fetchPokemonList(
        page: Int
    ): ApiResponse<PokemonResponse> =
        pokedexService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    suspend fun fetchPokemonDetail(
        name: String
    ): ApiResponse<PokemonDetailResponse> =
        pokedexService.fetchPokemonDetail(
            name = name
        )

    companion object {
        private const val PAGING_SIZE = 10
    }
}
