package com.arudo.catatube.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.ui.detail.movie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_movie_tv.view.*

class MovieFavoriteAdapter internal constructor() :
    PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class MovieFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieEntity: MovieEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.photo, movieEntity.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageShow)
                titleShow.text = movieEntity.title
                overviewShow.text = movieEntity.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DETAIL, movieEntity.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        return MovieFavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_tv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val movieFavorite = getItem(position)
        movieFavorite?.let { holder.bind(it) }
    }

    fun getSwipedData(moviePosition: Int): MovieEntity? = getItem(moviePosition)

}