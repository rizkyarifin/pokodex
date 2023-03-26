package id.rizky.arifin.ui.pokemontype

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import id.rizky.arifin.R
import id.rizky.arifin.core.model.PokemonData
import id.rizky.arifin.databinding.ItemPokemonTableBinding

class PokemonAdapter(val onItemClick: (String) -> Unit) :
    BindingListAdapter<PokemonData, PokemonAdapter.PokemonListViewHolder>(
        diffUtil
    ) {

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) =
        holder.bindPokemonData(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return parent.binding<ItemPokemonTableBinding>(R.layout.item_pokemon_table)
            .let(::PokemonListViewHolder)
    }

    inner class PokemonListViewHolder constructor(
        private val binding: ItemPokemonTableBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindPokemonData(pokemonData: PokemonData) {
            binding.root.setOnClickListener {
                onItemClick.invoke(pokemonData.pokemon.name)
            }
            binding.pokemon = pokemonData.pokemon
            binding.tvPokedexId.text = "# ${pokemonData.pokemon.getId()}"
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PokemonData>() {

            override fun areItemsTheSame(oldItem: PokemonData, newItem: PokemonData): Boolean =
                oldItem.pokemon.name == newItem.pokemon.name


            override fun areContentsTheSame(oldItem: PokemonData, newItem: PokemonData): Boolean =
                oldItem == newItem
        }
    }
}