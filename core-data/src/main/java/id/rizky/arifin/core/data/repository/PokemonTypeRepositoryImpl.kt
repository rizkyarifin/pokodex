package id.rizky.arifin.core.data.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import id.rizky.arifin.core.network.Dispatcher
import id.rizky.arifin.core.network.PokedexAppDispatchers
import id.rizky.arifin.core.network.service.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class PokemonTypeRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : PokemonTypeRepository {

    @WorkerThread
    override fun fetchPokemonType(
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = pokedexClient.fetchPokemonType()

        response.suspendOnSuccess {
            emit(data.results)
        }.onFailure {
            onError(message())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun fetchPokemonByType(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = pokedexClient.fetchPokemonByType(name)
        response.suspendOnSuccess {
            kotlinx.coroutines.delay(1000)
            emit(data.results.take(10))
        }.onFailure {
            onError(message())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}