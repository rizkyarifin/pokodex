package id.rizky.arifin.core.data.repository

import androidx.annotation.WorkerThread
import id.rizky.arifin.core.model.Pokemon
import id.rizky.arifin.core.model.PokemonData
import kotlinx.coroutines.flow.Flow

interface PokemonTypeRepository {
    @WorkerThread
    fun fetchPokemonType(
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>

    @WorkerThread
    fun fetchPokemonByType(
        name : String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<PokemonData>>
}