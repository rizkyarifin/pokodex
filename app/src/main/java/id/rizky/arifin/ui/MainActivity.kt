package id.rizky.arifin.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import id.rizky.arifin.R
import id.rizky.arifin.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            val drawerLayout: DrawerLayout = drawerLayout
            val navView: NavigationView = navView
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            navView.setupWithNavController(navController)

            toolbar.btnOpenDrawer.setOnClickListener {
                toggleShowDrawer(drawerLayout)
            }

            onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        toggleShowDrawer(drawerLayout)
                    } else {
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            })

            vm = viewModel
            adapter = MenuDrawerAdapter { titleMenuDrawer ->
                toggleShowDrawer(drawerLayout)
                navController.navigateUp()
                if (titleMenuDrawer == "Pokemon Type") {
                    navController.navigate(R.id.action_nav_home_to_nav_pokemon_type)
                } else {
                    navController.navigateUp()
                }
            }

        }
    }

    private fun toggleShowDrawer(drawerLayout: DrawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
            val btnCloseDrawer = drawerLayout.findViewById<ImageView>(R.id.btn_close_drawer)
            val imageLogo = drawerLayout.findViewById<ImageView>(R.id.img_logo_drawer)
            Glide.with(this).load(R.mipmap.ic_logo).into(imageLogo)
            btnCloseDrawer.setOnClickListener {
                toggleShowDrawer(drawerLayout)
            }
        }
    }
}