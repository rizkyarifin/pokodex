package id.rizky.arifin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.skydoves.bindables.BindingFragment
import id.rizky.arifin.R
import id.rizky.arifin.databinding.FragmentHomeBinding

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    IntroFragment.IntroFragmentListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            viewPager.isUserInputEnabled = false
            viewPager.adapter = ScreenSlidePagerAdapter(
                requireActivity()
            )
            Glide.with(this@HomeFragment).load(R.drawable.ic_background_home)
                .into(imgBackgroundHome)
        }
    }

    private inner class ScreenSlidePagerAdapter(
        fa: FragmentActivity
    ) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment =
            if (position == 0) IntroFragment().apply {
                introFragmentListener = this@HomeFragment
            } else PokeDexListFragment()

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClickCheckPokeDex() {
        binding {
            viewPager.setCurrentItem(1, true)
        }
    }
}