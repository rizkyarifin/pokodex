package id.rizky.arifin.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            layoutDetail.vm = viewModel
            layoutSprites.vm = viewModel
            layoutStat.vm = viewModel
            layoutDetail.rvAbilities.adapter = AbilityAdapter()
            layoutSprites.rvSprites.adapter = SpritesAdapter()
            layoutStat.rvStat.adapter = StatAdapter()
        }
    }
}