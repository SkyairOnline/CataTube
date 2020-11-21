package com.arudo.catatube.ui.favorite.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.ui.detail.tv.DetailTelevisionActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_movie_tv.view.*

class TvFavoriteAdapter internal constructor() :
    PagedListAdapter<TVEntity, TvFavoriteAdapter.TVFavoriteViewHolder>(
        diffCallback
    ) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TVEntity>() {
            override fun areItemsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TVFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvEntity: TVEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.photo, tvEntity.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageShow)
                titleShow.text = tvEntity.name
                overviewShow.text = tvEntity.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTelevisionActivity::class.java)
                    intent.putExtra(DetailTelevisionActivity.EXTRA_DETAIL, tvEntity.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVFavoriteViewHolder =
        TVFavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_tv, parent, false)
        )

    override fun onBindViewHolder(holder: TVFavoriteViewHolder, position: Int) {
        val televisionFavorite = getItem(position)
        televisionFavorite?.let { holder.bind(it) }
    }

    fun getSwipedData(televisionPosition: Int): TVEntity? = getItem(televisionPosition)

}