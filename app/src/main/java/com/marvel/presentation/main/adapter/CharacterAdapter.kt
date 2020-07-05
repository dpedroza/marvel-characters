package com.marvel.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.presentation.model.CharacterViewObject
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(
    val onOpenDetails: (CharacterViewObject) -> Unit = {},
    val onFavorite: (CharacterViewObject) -> Unit = {},
    val hideStars: Boolean = false
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<CharacterViewObject>()

    fun updateCharacters(characters: List<CharacterViewObject>) {
        val oldSize = this.characters.size
        this.characters.addAll(characters)
        val newSize = this.characters.size
        notifyItemRangeInserted(oldSize, newSize)
    }

    fun clear() {
        this.characters.clear()
        notifyDataSetChanged()
    }

    fun onUpdateFavorite(character: CharacterViewObject) {
        val index = characters.indexOf(character)
        notifyItemChanged(index)
    }

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterViewObject = characters[position]
        holder.bind(characterViewObject)
    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(viewObject: CharacterViewObject) {

            with(itemView) {

                setOnClickListener { onOpenDetails(viewObject) }

                val isFavorite = viewObject.isFavorite
                val favorite = R.drawable.ic_baseline_star_24
                val unpopular = R.drawable.ic_baseline_star_border_24
                val drawableId = if (isFavorite) {
                    favorite
                } else {
                    unpopular
                }
                val drawable = ContextCompat.getDrawable(context, drawableId)
                val name = viewObject.name
                val url = viewObject.bannerURL

                nameTextView.text = name

                if (hideStars) {
                    isEnabled = false
                    favoriteImageButton.visibility = GONE
                } else {
                    favoriteImageButton.setImageDrawable(drawable)
                    favoriteImageButton.setOnClickListener { onFavorite(viewObject) }
                }

                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .centerCrop()
                    .into(characterImageView)
            }
        }
    }
}
