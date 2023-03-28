package id.rizky.arifin.ui

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rizky.arifin.core.data.repository.PokemonTypeRepository
import id.rizky.arifin.core.model.MenuDrawer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonTypeRepository: PokemonTypeRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var menuDrawerList: List<MenuDrawer> by bindingProperty(
        listOf(
            MenuDrawer("Home", true),
            MenuDrawer("Pokemon Type", false)
        )
    )
        private set

    init {
        viewModelScope.launch {
            pokemonTypeRepository.fetchPokemonType(
                onComplete = { isLoading = false },
                onError = { toastMessage = it }
            ).collectLatest {
                menuDrawerList[1].subMenu = listOf()
                menuDrawerList[1].subMenu = mutableListOf<MenuDrawer>().apply {
                    it.forEach {
                        this.add(MenuDrawer(it.name, false))
                    }
                }
            }
        }
    }

    fun resetMenuState(title: String) {
        menuDrawerList.forEach {
            it.isActive = it.title == title
        }
    }

    fun resetSubMenuState(title: String) {
        menuDrawerList[1].subMenu.forEach {
            it.isActive = it.title == title
        }
    }
}