package id.rizky.arifin.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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

            layoutDetail.adapter = AbilityAdapter()
            layoutSprites.adapter = SpritesAdapter()
            layoutStat.adapter = StatAdapter()

            layoutDetail.vm = viewModel
            layoutSprites.vm = viewModel
            layoutStat.vm = viewModel

        }
    }
}