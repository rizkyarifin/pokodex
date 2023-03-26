package id.rizky.arifin.core.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import id.rizky.arifin.core.model.Pokemon
import id.rizky.arifin.core.network.Dispatcher
import id.rizky.arifin.core.network.PokedexAppDispatchers
import id.rizky.arifin.core.network.service.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : HomeRepository {

    val pokemonsTemp = mutableListOf<Pokemon>()

    @WorkerThread
    override fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = pokedexClient.fetchPokemonList(page = page)

        response.suspendOnSuccess {
            val pokemons = data.results
            pokemons.forEach { pokemon ->
                pokemon.page = page
                pokemon.totalData = data.count
            }
            pokemonsTemp.addAll(pokemons)
            val newData = pokemonsTemp.toList()
            kotlinx.coroutines.delay(1000)
            emit(newData)
        }.onFailure {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}