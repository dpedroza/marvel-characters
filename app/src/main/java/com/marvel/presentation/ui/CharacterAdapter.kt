package com.marvel.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.presentation.model.CharacterViewObject
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<CharacterViewObject>()

    fun updateCharacters(characters: List<CharacterViewObject>, clearList: Boolean = false) {
        if (clearList) this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_character,
            parent,
            false
        )
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val characterViewObject = characters[position]
        holder.bind(characterViewObject)
    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(viewObject: CharacterViewObject) {

            itemView.nameTextView.text = viewObject.name
            Glide.with(itemView.context)
                .load(viewObject.bannerURL)
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .centerCrop()
                .into(itemView.characterImageView)
        }
    }
}

