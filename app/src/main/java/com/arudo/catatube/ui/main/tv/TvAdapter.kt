package com.arudo.catatube.ui.main.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.ui.detail.tv.DetailTelevisionActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_movie_tv.view.*

class TvAdapter : RecyclerView.Adapter<TvAdapter.TVViewHolder>() {
    private val televisionData = ArrayList<TelevisionResultsItem>()

    fun setData(items: ArrayList<TelevisionResultsItem>) {
        televisionData.clear()
        televisionData.addAll(items)
        notifyDataSetChanged()
    }

    inner class TVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(televisionResultsItem: TelevisionResultsItem) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w440_and_h660_face${televisionResultsItem.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageShow)
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