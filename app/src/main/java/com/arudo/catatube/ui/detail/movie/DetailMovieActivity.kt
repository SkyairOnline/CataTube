package com.arudo.catatube.ui.detail.movie

import android.icu.math.BigDecimal
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.animation.FloatingActionIconAnimation
import com.arudo.catatube.viewmodel.ViewModelFactory
import com.arudo.catatube.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var favoriteBtn: FloatingActionButton
    private lateinit var floatingActionIconAnimation: FloatingActionIconAnimation
    private var indexFavorite: Boolean = false
    private var nameMovie: String = ""

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    @RequiresApi(Build.VERSION_CODES.N)
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
        val movieId = intent.extras?.getInt(EXTRA_DETAIL) ?: return
        favoriteBtn = findViewById(R.id.favoriteBtn)
        floatingActionIconAnimation = FloatingActionIconAnimation(this)
        floatingActionIconAnimation.floatingActionButton = favoriteBtn
        detailMovieViewModel.setDetailMovie(movieId)
        detailMovieViewModel.getDetailMovie().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        layoutDetailConstraint.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        nameMovie = it.data?.title.toString()
                        imgShow.tag = it.data?.posterPath
                        Glide.with(this)
                            .load(getString(R.string.photo, it.data?.posterPath))
                            .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                                    .transform(CenterCrop(), RoundedCorners(10))
                                    .error(R.drawable.ic_broken_image)
                            )
                            .into(imgShow)
                        imgBackground.tag = it.data?.posterPath
                        Glide.with(this)
                            .load(getString(R.string.photo, it.data?.posterPath))
                            .centerCrop()
                            .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                                    .transform(CenterCrop(), RoundedCorners(10))
                                    .error(R.drawable.ic_broken_image)
                            )
                            .into(imgBackground)
                        txtTitle.text = it.data?.title
                        txtSubTitle.text =
                            getString(
                                R.string.txtSubtitleMovie, it.data?.releaseDate,
                                it.data?.runtime?.div(60), it.data?.runtime?.rem(60)
                            )
                        txtRating.text =
                            getString(R.string.txtRating, it.data?.voteAverage?.times(10))
                        txtQuote.text = it.data?.tagline
                        txtOverview.text = it.data?.overview
                        txtStatus.text = it.data?.status
                        txtBudget.text = getString(R.string.price,
                            it.data?.budget?.let { it1 -> priceFormatter(it1) })
                        txtRevenue.text =
                            getString(R.string.price,
                                it.data?.revenue?.let { it1 -> priceFormatter(it1) })
                        floatingActionIconAnimation.icon(indexFavorite)
                        progressBar.visibility = View.GONE
                        layoutDetailConstraint.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        layoutDetailConstraint.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "There is an error. Please check the internet or contact the system administrator",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
        detailMovieViewModel.getFavoriteMovie(movieId).observe(this, {
            if (it != null) {
                indexFavorite = it.id == movieId
            }
        })
        floatingActionIconAnimation.icon(indexFavorite)
        favoriteBtn.setOnClickListener {
            indexFavorite = !indexFavorite
            floatingActionIconAnimation.icon(indexFavorite)
            if (indexFavorite) {
                detailMovieViewModel.setFavoriteMovie(movieId)
                Toast.makeText(
                    it.context,
                    "You just added $nameMovie from your Movie Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                detailMovieViewModel.deleteFavoriteMovie(movieId)
                Toast.makeText(
                    it.context,
                    "You just removed $nameMovie from your Movie Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun priceFormatter(price: Double): String {
        val decimalFormat = DecimalFormat(getString(R.string.patternPrice))
        return decimalFormat.format(BigDecimal(price))
    }
}