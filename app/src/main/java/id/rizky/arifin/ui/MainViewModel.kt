package id.rizky.arifin.ui

import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import id.rizky.arifin.core.model.MenuDrawer

class MainViewModel() : BindingViewModel() {

    @get:Bindable
    var menuDrawerList: List<MenuDrawer> by bindingProperty(
        listOf(
            MenuDrawer("Home", true),
            MenuDrawer("Pokemon Type", false)
        )
    )
        private set
}