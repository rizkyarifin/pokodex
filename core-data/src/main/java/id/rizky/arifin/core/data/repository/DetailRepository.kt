package id.rizky.arifin.core.data.repository

import androidx.annotation.WorkerThread
import id.rizky.arifin.core.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    @WorkerThread
    fun fetchPokemonDetail(
        name : String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<PokemonDetail>
}