package id.rizky.arifin.ui.detail

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import id.rizky.arifin.core.data.repository.DetailRepository
import id.rizky.arifin.core.model.PokemonDetail
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
    detailRepository: DetailRepository,
    @Assisted private val pokemonName: String
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val pokemonDetailFlow =
        detailRepository.fetchPokemonDetail(
            name = pokemonName,
            onComplete = { isLoading = false },
            onError = { toastMessage = it }
        )

    @get:Bindable
    val pokemonDetail: PokemonDetail? by pokemonDetailFlow.asBindingProperty(
        viewModelScope,
        null
    )

    init {
        Timber.d("init DetailViewModel")
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(pokemonName: String): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            pokemonName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(pokemonName) as T
            }
        }
    }
}