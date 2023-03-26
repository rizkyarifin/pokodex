package id.rizky.arifin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IntroFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    interface IntroFragmentListener {
        fun onClickCheckPokeDex()
    }
}