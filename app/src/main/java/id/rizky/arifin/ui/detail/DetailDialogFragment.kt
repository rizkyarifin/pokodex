package id.rizky.arifin.ui.detail

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skydoves.bindables.BindingBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import id.rizky.arifin.R
import id.rizky.arifin.databinding.FragmentDetailDialogBinding
import javax.inject.Inject

@AndroidEntryPoint
class DetailDialogFragment :
    BindingBottomSheetDialogFragment<FragmentDetailDialogBinding>(R.layout.fragment_detail_dialog) {

    @Inject
    internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    private val pokemonName: String by lazy {
        arguments?.getString("pokemonName").toString()
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(
            detailViewModelFactory, pokemonName
        )
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme).also {
            val metrics = resources.displayMetrics
            it.behavior.peekHeight = metrics.heightPixels
            it.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding {
            vm = viewModel
            contentDetail.vm = viewModel
            contentDetail.adapter = AbilityAdapter()

            btnMoreDetail.setOnClickListener {
                val args = Bundle().also {
                    it.putString("pokemonName", pokemonName)
                }
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigateUp()
                requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.action_nav_home_to_detailFragment, args)
            }
        }
    }
}