package com.marvel.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.domain.characters.entity.ComicsEntity
import kotlinx.android.synthetic.main.item_comics.view.*

class ComicsAdapter : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    private val comics = mutableListOf<ComicsEntity>()

    fun updateComics(characters: List<ComicsEntity>) {
        val oldSize = this.comics.size
        this.comics.addAll(characters)
        val newSize = this.comics.size
        notifyItemRangeInserted(oldSize, newSize)
    }

    override fun getItemCount() = comics.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_comics, parent, false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val characterViewObject = comics[position]
        holder.bind(characterViewObject)
    }

    inner class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(series: ComicsEntity) {

            with(itemView) {

                val name = series.name
                val url = series.imageUrl

                comicsTitleTextView.text = name

                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .centerCrop()
                    .into(comicsImageView)
            }
        }
    }
}
