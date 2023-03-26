package id.rizky.arifin.core.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import id.rizky.arifin.core.data.mapper.DataEntityMapper.mapEntityToDomain
import id.rizky.arifin.core.network.Dispatcher
import id.rizky.arifin.core.network.PokedexAppDispatchers
import id.rizky.arifin.core.network.service.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {

    @WorkerThread
    override fun fetchPokemonDetail(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = pokedexClient.fetchPokemonDetail(name = name)

        response.suspendOnSuccess {
            emit(data.mapEntityToDomain())
        }.onFailure {
            onError(message())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}