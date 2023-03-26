package id.rizky.arifin.core.model

data class MenuDrawer(
    val title: String,
    var isActive: Boolean,
    var subMenu: List<MenuDrawer> = arrayListOf()
)
