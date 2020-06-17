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

        private val characters = emptyList<CharacterViewObject>().toMutableList()

        fun updateCharacters(spotlights: List<CharacterViewObject>) {
            this.characters.clear()
            this.characters.addAll(spotlights)
            notifyDataSetChanged()
        }

        override fun getItemCount() = characters.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
            val context = parent.context
            val layout = R.layout.item_character
            val view = LayoutInflater.from(context).inflate(layout, parent, false)
            return CharacterViewHolder(
                view
            )
        }

        override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
            val characterViewObject = characters[position]
            holder.bind(characterViewObject)
        }

        class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(viewObject: CharacterViewObject) {
                itemView.nameTextView.text = viewObject.name
                Glide.with(itemView.context)
                    .load(viewObject.bannerURL)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(itemView.characterImageView)
            }
        }
    }
