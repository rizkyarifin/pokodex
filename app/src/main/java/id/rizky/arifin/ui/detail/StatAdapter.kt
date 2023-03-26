package id.rizky.arifin.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import id.rizky.arifin.R
import id.rizky.arifin.core.model.Stat
import id.rizky.arifin.databinding.ItemPokemonStatBinding

class StatAdapter : BindingListAdapter<Stat, StatAdapter.StatViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Stat>() {

            override fun areItemsTheSame(oldItem: Stat, newItem: Stat): Boolean =
                oldItem.name == newItem.name


            override fun areContentsTheSame(oldItem: Stat, newItem: Stat): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) =
        holder.bindSprites(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder =
        parent.binding<ItemPokemonStatBinding>(R.layout.item_pokemon_stat)
            .let(::StatViewHolder)

    inner class StatViewHolder constructor(
        private val binding: ItemPokemonStatBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindSprites(stat: Stat) {
            binding.stat = stat
        }
    }
}