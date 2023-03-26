package id.rizky.arifin.ui

import android.content.Context
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
import id.rizky.arifin.databinding.ItemMenuDrawerBinding

class MenuDrawerAdapter(val itemOnClick: (String) -> Unit) :
    BindingListAdapter<MenuDrawer, MenuDrawerAdapter.MenuDrawerViewHolder>(
        diffUtil
    ) {

    private var context: Context? = null

    override fun onBindViewHolder(holder: MenuDrawerViewHolder, position: Int) =
        holder.bindMenuDrawer(getItem(position))

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDrawerViewHolder {
        context = parent.context;
        return parent.binding<ItemMenuDrawerBinding>(R.layout.item_menu_drawer)
            .let(::MenuDrawerViewHolder)
    }

    inner class MenuDrawerViewHolder constructor(
        private val binding: ItemMenuDrawerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
        }

        fun bindMenuDrawer(menuDrawer: MenuDrawer) {
            binding.root.setOnClickListener {
                itemOnClick.invoke(menuDrawer.title)
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
                    binding.tvMenuDrawer.typeface = ResourcesCompat.getFont(it, R.font.poppins_bold)
                }

            } else {
                context.whatIfNotNullAs<Context> {
                    binding.tvMenuDrawer.setTextColor(
                        AppCompatResources.getColorStateList(
                            it,
                            R.color.darkgrey
                        )
                    )
                    binding.tvMenuDrawer.typeface = ResourcesCompat.getFont(it, R.font.poppins_regular)
                }
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