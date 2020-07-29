package com.marvel.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.domain.characters.model.entity.SeriesEntity
import kotlinx.android.synthetic.main.item_series.view.*

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private val series = mutableListOf<SeriesEntity>()

    fun updateSeries(characters: List<SeriesEntity>) {
        val oldSize = this.series.size
        this.series.addAll(characters)
        val newSize = this.series.size
        notifyItemRangeInserted(oldSize, newSize)
    }

    override fun getItemCount() = series.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_series, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val characterViewObject = series[position]
        holder.bind(characterViewObject)
    }

    inner class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(series: SeriesEntity) {

            with(itemView) {

                val name = series.name
                val url = series.imageUrl

                seriesTitleTextView.text = name

                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .centerCrop()
                    .into(seriesImageView)
            }
        }
    }
}
