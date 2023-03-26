package id.rizky.arifin.binding

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.skydoves.androidribbon.RibbonRecyclerView
import com.skydoves.androidribbon.ribbonView
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import id.rizky.arifin.R
import id.rizky.arifin.core.model.Pokemon
import id.rizky.arifin.utils.PokemonColorsUtils
import id.rizky.arifin.utils.SpacesItemDecoration

object ViewBinding {
    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("totalData")
    fun bindTotalDataPokedex(textView: TextView, pokemonList: List<Pokemon>?) {
        textView.text = "All Generation totaling 0 Pokemon"
        pokemonList.whatIfNotNullOrEmpty {
            textView.text = "All Generation totaling ${it[0].totalData} Pokemon"
        }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl.whatIfNotNull {
            Glide.with(imageView.context).load(it).placeholder(android.R.color.transparent).into(
                imageView
            )
        }
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text.whatIfNotNullOrEmpty {
            Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("bindPokemonTypes")
    fun bindPokemonTypes(recyclerView: RibbonRecyclerView, types: List<String>?) {
        types.whatIfNotNullOrEmpty {
            recyclerView.clear()
            for (type in it) {
                with(recyclerView) {
                    addRibbon(
                        ribbonView(context) {
                            setText(type)
                            setTextColor(Color.WHITE)
                            setPaddingLeft(30f)
                            setPaddingRight(30f)
                            setPaddingTop(10f)
                            setPaddingBottom(10f)
                            setTextSize(12f)
                            setRibbonRadius(100f)
                            setTextStyle(Typeface.BOLD)
                            setRibbonBackgroundColorResource(
                                PokemonColorsUtils.getTypeColor(type)
                            )
                        }.apply {
                            maxLines = 2
                            gravity = Gravity.START
                        }
                    )
                    addItemDecoration(SpacesItemDecoration())
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("shapeBackground")
    fun bindShapeBackground(view: View, stat: String) {
        when (stat) {
            "hp" -> view.setBackgroundResource(R.drawable.shape_circle_red)
            "defense" -> view.setBackgroundResource(R.drawable.shape_circle_orange)
            "special-defense" -> view.setBackgroundResource(R.drawable.shape_cricle_green)
            "attack" -> view.setBackgroundResource(R.drawable.shape_circle_blue)
            "special-attack" -> view.setBackgroundResource(R.drawable.shape_circle_yellow)
            else -> view.setBackgroundResource(R.drawable.shape_circle_ghost)
        }
    }

    @JvmStatic
    @BindingAdapter("textColorStat")
    fun bindTextColorStat(view: TextView, stat: String) {
        view.setTextColor(
            AppCompatResources.getColorStateList(
                view.context,
                PokemonColorsUtils.getStatColor(stat)
            )
        )
    }

    @JvmStatic
    @BindingAdapter("tintColorType")
    fun bindTintColorType(view: ImageView, type: String) {
        view.setColorFilter(
            ContextCompat.getColor(
                view.context,
                PokemonColorsUtils.getTypeColor(type)
            )
        );
    }
}