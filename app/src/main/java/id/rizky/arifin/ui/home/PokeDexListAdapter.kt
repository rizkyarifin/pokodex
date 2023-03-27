package id.rizky.arifin.ui.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import id.rizky.arifin.R
import id.rizky.arifin.core.model.Pokemon
import id.rizky.arifin.databinding.ItemPokedexListBinding

class PokeDexListAdapter(val onItemClick: (String) -> Unit) :
    BindingListAdapter<Pokemon, PokeDexListAdapter.PokeDexListViewHolder>(
        diffUtil
    ) {

    override fun onBindViewHolder(holder: PokeDexListViewHolder, position: Int) =
        holder.bindPokemon(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeDexListViewHolder {
        return parent.binding<ItemPokedexListBinding>(R.layout.item_pokedex_list)
            .let(::PokeDexListViewHolder)
    }

    inner class PokeDexListViewHolder constructor(
        private val binding: ItemPokedexListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindPokemon(pokemon: Pokemon) {
            binding.root.setOnClickListener {
                onItemClick.invoke(pokemon.name)
            }
            binding.pokemon = pokemon
            binding.tvPokedexId.text = "# ${pokemon.getId()}"
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name


            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }

}