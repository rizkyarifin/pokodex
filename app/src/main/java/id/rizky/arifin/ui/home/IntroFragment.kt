package id.rizky.arifin.ui.home

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.skydoves.bindables.BindingFragment
import id.rizky.arifin.R
import id.rizky.arifin.databinding.FragmentIntroBinding

class IntroFragment : BindingFragment<FragmentIntroBinding>(R.layout.fragment_intro) {

    var introFragmentListener: IntroFragmentListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            Glide.with(requireContext()).load(R.mipmap.ic_home_pokemon).into(imgContentHome)
            btnCheckPokedex.setOnClickListener {
                introFragmentListener?.onClickCheckPokeDex()
            }
        }
    }

    interface IntroFragmentListener {
        fun onClickCheckPokeDex()
    }
}