package id.rizky.arifin.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import id.rizky.arifin.R
import id.rizky.arifin.databinding.FragmentDetailBinding
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BindingFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    @Inject
    internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(
            detailViewModelFactory,
            arguments?.getString("pokemonName").toString()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            vm = viewModel
            layoutDetail.vm = viewModel
            layoutSprites.vm = viewModel
            layoutStat.vm = viewModel

            layoutDetail.abilityAdapter = AbilityAdapter()
            layoutDetail.typeAdapter = TypeAdapter { type ->
                val args = Bundle().also {
                    it.putString("pokemonType", type)
                }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.action_detailFragment_to_nav_pokemon_type, args)
            }
            layoutSprites.adapter = SpritesAdapter()
            layoutStat.adapter = StatAdapter()

        }
    }
}