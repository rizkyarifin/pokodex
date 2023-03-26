package id.rizky.arifin.ui.home

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rizky.arifin.core.data.repository.HomeRepository
import id.rizky.arifin.core.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokeDexListViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
        homeRepository.fetchPokemonList(
            page = page,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { toastMessage = it }
        )
    }

    @get:Bindable
    val pokemonList: List<Pokemon> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

    @MainThread
    fun fetchNextPokemonList() {
        if (!isLoading) {
            pokemonFetchingIndex.value++
        }
    }
}
