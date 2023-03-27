package id.rizky.arifin.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import com.skydoves.whatif.whatIfNotNullAs
import id.rizky.arifin.R
import id.rizky.arifin.core.model.MenuDrawer
import id.rizky.arifin.databinding.ItemDrawerSubmenuBinding
import id.rizky.arifin.databinding.ItemMenuDrawerBinding
import id.rizky.arifin.utils.PokemonColorsUtils

class MenuDrawerAdapter(
    val itemOnClick: (String, Int) -> Unit,
    val itemSubMenuOnClick: (String, Int) -> Unit
) :
    BindingListAdapter<MenuDrawer, MenuDrawerAdapter.MenuDrawerViewHolder>(
        diffUtil
    ) {

    private var context: Context? = null

    override fun onBindViewHolder(holder: MenuDrawerViewHolder, position: Int) =
        holder.bindMenuDrawer(getItem(position))

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDrawerViewHolder {
        context = parent.context
        return parent.binding<ItemMenuDrawerBinding>(R.layout.item_menu_drawer)
            .let(::MenuDrawerViewHolder)
    }

    inner class MenuDrawerViewHolder constructor(
        private val binding: ItemMenuDrawerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindMenuDrawer(menuDrawer: MenuDrawer) {
            /**
             * Main Menu Binding
             */
            binding.root.setOnClickListener {
                itemOnClick.invoke(menuDrawer.title, bindingAdapterPosition)
            }
            binding.menuDrawer = menuDrawer

            if (menuDrawer.isActive) {
                context.whatIfNotNullAs<Context> {
                    binding.tvMenuDrawer.setTextColor(
                        AppCompatResources.getColorStateList(
                            it,
                            R.color.yellow
                        )
                    )
                    binding.tvMenuDrawer.typeface =
                        ResourcesCompat.getFont(it, R.font.poppins_bold)
                }

            } else {
                context.whatIfNotNullAs<Context> {
                    binding.tvMenuDrawer.setTextColor(
                        AppCompatResources.getColorStateList(
                            it,
                            R.color.darkgrey
                        )
                    )
                    binding.tvMenuDrawer.typeface =
                        ResourcesCompat.getFont(it, R.font.poppins_regular)
                }
            }

            /**
             * Nested Sub Menu Binding
             */
            if (menuDrawer.title == "Pokemon Type" && menuDrawer.isActive) {
                binding.layoutSubMenu.visibility = View.VISIBLE
                binding.layoutSubMenu.removeAllViews()
                menuDrawer.subMenu.forEach { subMenuDrawer ->
                    val itemSubMenuBinding =
                        (binding.root as ViewGroup).binding<ItemDrawerSubmenuBinding>(R.layout.item_drawer_submenu)
                    itemSubMenuBinding.tvMenuDrawer.text = subMenuDrawer.title
                    binding.layoutSubMenu.addView(itemSubMenuBinding.root)

                    itemSubMenuBinding.root.setOnClickListener {
                        itemSubMenuOnClick.invoke(
                            subMenuDrawer.title,
                            subMenuDrawer.subMenu.indexOf(subMenuDrawer)
                        )
                    }

                    if (subMenuDrawer.isActive) {
                        context.whatIfNotNullAs<Context> {
                            itemSubMenuBinding.tvMenuDrawer.setTextColor(
                                AppCompatResources.getColorStateList(
                                    it,
                                    PokemonColorsUtils.getTypeColor(subMenuDrawer.title)
                                )
                            )
                            itemSubMenuBinding.tvMenuDrawer.typeface =
                                ResourcesCompat.getFont(it, R.font.poppins_bold)
                        }

                    } else {
                        context.whatIfNotNullAs<Context> {
                            itemSubMenuBinding.tvMenuDrawer.setTextColor(
                                AppCompatResources.getColorStateList(
                                    it,
                                    R.color.darkgrey
                                )
                            )
                            itemSubMenuBinding.tvMenuDrawer.typeface =
                                ResourcesCompat.getFont(it, R.font.poppins_regular)
                        }
                    }
                }
            } else {
                binding.layoutSubMenu.visibility = View.GONE
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MenuDrawer>() {

            override fun areItemsTheSame(oldItem: MenuDrawer, newItem: MenuDrawer): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: MenuDrawer, newItem: MenuDrawer): Boolean =
                oldItem == newItem
        }
    }
}