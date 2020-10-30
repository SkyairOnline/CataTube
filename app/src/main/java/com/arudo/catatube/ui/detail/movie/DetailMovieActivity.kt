package com.arudo.catatube.ui.detail.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.title = getString(R.string.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar.visibility = View.VISIBLE
        layoutDetailConstraint.visibility = View.GONE
        detailMovieViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        ).get(
            DetailMovieViewModel::class.java
        )
        val movieTelevisionId = intent.extras?.getInt(EXTRA_DETAIL) ?: return
        detailMovieViewModel.setDetailMovie(movieTelevisionId)
        detailMovieViewModel.getDetailMovie().observe(this, {
            if (it != null) {
                progressBar.visibility = View.GONE
                layoutDetailConstraint.visibility = View.VISIBLE
                imgShow.tag = it.posterPath
                Glide.with(this)
                    .load(getString(R.string.photo, it.posterPath))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgShow)
                imgBackground.tag = it.posterPath
                Glide.with(this)
                    .load(getString(R.string.photo, it.posterPath))
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgBackground)
                txtTitle.text = it.title
                txtSubTitle.text =
                    getString(
                        R.string.txtSubtitle, it.releaseDate,
                        it.runtime?.div(60), it.runtime?.rem(60)
                    )
                txtRating.text = getString(R.string.txtRating, it.voteAverage?.times(10))
                txtQuote.text = it.tagline
                txtOverview.text = it.overview
                txtStatus.text = it.status
                txtBudget.text = getString(R.string.price, it.budget)
                txtRevenue.text = getString(R.string.price, it.revenue)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}