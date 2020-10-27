package com.arudo.catatube.ui.movietv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.data.source.local.entity.MovieTVEntity
import com.arudo.catatube.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageShow)
                titleShow.text = movieTVEntity.title
                overviewShow.text = movieTVEntity.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, movieTVEntity.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, movieTVEntity.type)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTvViewHolder =
        MovieTvViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_tv, parent, false)
        )

    override fun onBindViewHolder(holder: MovieTvViewHolder, position: Int) {
        holder.bind(movieTVData[position])
    }

    override fun getItemCount(): Int = movieTVData.size

}