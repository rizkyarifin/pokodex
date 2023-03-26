package id.rizky.arifin.core.data.repository

import androidx.annotation.WorkerThread
import id.rizky.arifin.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    @WorkerThread
    fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>
}