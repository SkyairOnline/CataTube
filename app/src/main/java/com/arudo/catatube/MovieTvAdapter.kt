package com.arudo.catatube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.data.MovieTVEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_movie_tv.view.*

class MovieTvAdapter : RecyclerView.Adapter<MovieTvAdapter.MovieTvViewHolder>() {
    private val movieTVData = ArrayList<MovieTVEntity>()

    fun setData(items: ArrayList<MovieTVEntity>) {
        movieTVData.clear()
        movieTVData.addAll(items)
    }

    inner class MovieTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieTVEntity: MovieTVEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(movieTVEntity.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageShow)
                titleShow.text = movieTVEntity.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTvViewHolder =
        MovieTvViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_tv, parent, false)
        )

    override fun onBindViewHolder(holder: MovieTvAdapter.MovieTvViewHolder, position: Int) {
        holder.bind(movieTVData[position])
    }

    override fun getItemCount(): Int = movieTVData.size

}