package id.rizky.arifin.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import id.rizky.arifin.R
import id.rizky.arifin.databinding.ItemPokemonAbilityBinding

class AbilityAdapter : BindingListAdapter<String, AbilityAdapter.AbilityViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) =
        holder.bindAbility(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return parent.binding<ItemPokemonAbilityBinding>(R.layout.item_pokemon_ability)
            .let(::AbilityViewHolder)
    }

    inner class AbilityViewHolder constructor(
        private val binding: ItemPokemonAbilityBinding
    ) : ViewHolder(binding.root) {

        fun bindAbility(ability: String) {
            binding.ability = ability
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }
}