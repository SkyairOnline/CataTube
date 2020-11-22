package com.arudo.catatube.ui.main.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.ui.detail.movie.DetailMovieActivity
import com.arudo.catatube.utils.PosterContainer
import kotlinx.android.synthetic.main.item_list_movie_tv.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieData = ArrayList<MovieResultsItem>()

    fun setData(items: List<MovieResultsItem>) {
        movieData.clear()
        movieData.addAll(items)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieResultsItem: MovieResultsItem) {
            with(itemView) {
                PosterContainer(itemView.context, null, movieResultsItem.posterPath, imageShow)
                titleShow.text = movieResultsItem.title
                overviewShow.text = movieResultsItem.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DETAIL, movieResultsItem.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie_tv, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int = movieData.size

}