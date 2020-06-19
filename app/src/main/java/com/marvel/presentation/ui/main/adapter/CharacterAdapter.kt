package com.marvel.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.presentation.model.CharacterViewObject
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(
    val onFavorite: (CharacterViewObject) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<CharacterViewObject>()

    fun updateCharacters(
        characters: List<CharacterViewObject>? = null,
        clearList: Boolean = false
    ) {
        if (clearList) this.characters.clear()
        characters?.let {
            this.characters.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_character,
            parent,
            false
        )
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterViewObject = characters[position]
        holder.bind(characterViewObject)
    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(viewObject: CharacterViewObject) {

            itemView.nameTextView.text = viewObject.name

            if (viewObject.isFavorite) {
                itemView.favoriteImageButton.setImageDrawable(
                    itemView.context.resources.getDrawable(
                        R.drawable.ic_baseline_star_24
                    )
                )
            } else {
                itemView.favoriteImageButton.setImageDrawable(
                    itemView.context.resources.getDrawable(
                        R.drawable.ic_baseline_star_border_24
                    )
                )
            }

            itemView.favoriteImageButton.setOnClickListener { onFavorite(viewObject) }

            Glide.with(itemView.context)
                .load(viewObject.bannerURL)
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .centerCrop()
                .into(itemView.characterImageView)
        }
    }
}

