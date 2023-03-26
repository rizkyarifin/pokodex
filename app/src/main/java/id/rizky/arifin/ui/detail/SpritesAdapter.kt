package id.rizky.arifin.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import id.rizky.arifin.R
import id.rizky.arifin.databinding.ItemPokemonSpritesBinding

class SpritesAdapter : BindingListAdapter<String, SpritesAdapter.SpritesViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: SpritesViewHolder, position: Int) =
        holder.bindSprites(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpritesViewHolder =
        parent.binding<ItemPokemonSpritesBinding>(R.layout.item_pokemon_sprites)
            .let(::SpritesViewHolder)

    inner class SpritesViewHolder constructor(
        private val binding: ItemPokemonSpritesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindSprites(sprites: String) {

        }
    }
}