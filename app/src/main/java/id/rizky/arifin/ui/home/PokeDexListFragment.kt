package id.rizky.arifin.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import id.rizky.arifin.R
import id.rizky.arifin.databinding.FragmentPokedexListBinding

@AndroidEntryPoint
class PokeDexListFragment :
    BindingFragment<FragmentPokedexListBinding>(R.layout.fragment_pokedex_list) {

    val viewModel: PokeDexListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            adapter = PokeDexListAdapter { pokemonName ->
                val args = Bundle().also {
                    it.putString("pokemonName", pokemonName)
                }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.action_nav_home_to_detailDialogFragment, args)
            }
            vm = viewModel

            pokedexListScrollView.viewTreeObserver?.addOnScrollChangedListener {
                val view = pokedexListScrollView.getChildAt(pokedexListScrollView.childCount - 1)

                val diff =
                    view.bottom - (pokedexListScrollView.height + pokedexListScrollView.scrollY)

                if (diff == 0) {
                    viewModel.fetchNextPokemonList()
                }
            }
        }
    }

}