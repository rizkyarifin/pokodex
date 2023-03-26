package id.rizky.arifin.core.network.service

import com.skydoves.sandwich.ApiResponse
import id.rizky.arifin.core.network.model.mapper.PokemonDetailResponse
import id.rizky.arifin.core.network.model.mapper.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonDetail(
        @Path("name") name: String
    ): ApiResponse<PokemonDetailResponse>
}
