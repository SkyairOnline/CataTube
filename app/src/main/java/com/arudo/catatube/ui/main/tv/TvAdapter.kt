package com.arudo.catatube.ui.main.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.ui.detail.tv.DetailTelevisionActivity
import com.arudo.catatube.utils.PosterContainer
import kotlinx.android.synthetic.main.item_list_movie_tv.view.*

class TvAdapter : RecyclerView.Adapter<TvAdapter.TVViewHolder>() {
    private val televisionData = ArrayList<TelevisionResultsItem>()

    fun setData(items: List<TelevisionResultsItem>) {
        televisionData.clear()
        televisionData.addAll(items)
        notifyDataSetChanged()
    }

    inner class TVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(televisionResultsItem: TelevisionResultsItem) {
            with(itemView) {
                PosterContainer(itemView.context, null, televisionResultsItem.posterPath, imageShow)
                titleShow.text = televisionResultsItem.name
                overviewShow.text = televisionResultsItem.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTelevisionActivity::class.java)
                    intent.putExtra(DetailTelevisionActivity.EXTRA_DETAIL, televisionResultsItem.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder =
        TVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_tv, parent, false)
        )

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bind(televisionData[position])
    }

    override fun getItemCount(): Int = televisionData.size

}