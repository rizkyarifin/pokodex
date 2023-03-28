package id.rizky.arifin.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import id.rizky.arifin.R
import id.rizky.arifin.databinding.ItemPokemonTypeBinding

class TypeAdapter(val onItemClick : (String) -> Unit) : BindingListAdapter<String, TypeAdapter.TypeViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) =
        holder.bindType(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder =
        parent.binding<ItemPokemonTypeBinding>(R.layout.item_pokemon_type).let(::TypeViewHolder)


    inner class TypeViewHolder constructor(val binding: ItemPokemonTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindType(type: String) {
            binding.type = type
            binding.root.setOnClickListener {
                onItemClick.invoke(type)
            }
            binding.executePendingBindings()
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