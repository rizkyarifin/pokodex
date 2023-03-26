package id.rizky.arifin.ui.pokemontype

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import id.rizky.arifin.R
import id.rizky.arifin.databinding.FragmentPokemonTypeBinding
import id.rizky.arifin.ui.MainActivity
import javax.inject.Inject

@AndroidEntryPoint
class PokemonTypeFragment :
    BindingFragment<FragmentPokemonTypeBinding>(R.layout.fragment_pokemon_type) {

    @Inject
    internal lateinit var pokemonTypeViewModelFactory: PokemonTypeViewModel.AssistedFactory

    val pokemonType by lazy { arguments?.getString("pokemonType") }

    private val viewModel: PokemonTypeViewModel by viewModels {
        PokemonTypeViewModel.provideFactory(
            pokemonTypeViewModelFactory,
            pokemonType.toString()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            vm = viewModel
            adapter = PokemonAdapter() { pokemonName ->
                val args = Bundle().also {
                    it.putString("pokemonName", pokemonName)
                }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.action_nav_pokemon_type_to_detailFragment, args)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                if (isAdded) {
                    (requireActivity() as MainActivity).resetMenuStateHome()
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}