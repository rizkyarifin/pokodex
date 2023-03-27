package id.rizky.arifin.ui.pokemontype

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import id.rizky.arifin.core.data.repository.PokemonTypeRepository
import id.rizky.arifin.core.model.PokemonData

class PokemonTypeViewModel @AssistedInject constructor(
    private val pokemonTypeRepository: PokemonTypeRepository,
    @Assisted val pokemonType: String
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val pokemonTypeListFlow =
        pokemonTypeRepository.fetchPokemonByType(
            pokemonType,
            onComplete = { isLoading = false },
            onError = { toastMessage = it }
        )

    @get:Bindable
    val pokemon: List<PokemonData>? by pokemonTypeListFlow.asBindingProperty(
        viewModelScope,
        null
    )

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(pokemonType: String): PokemonTypeViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            pokemonType: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(pokemonType) as T
            }
        }
    }
}