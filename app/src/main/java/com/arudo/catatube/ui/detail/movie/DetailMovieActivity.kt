package com.arudo.catatube.ui.detail.movie

import android.icu.math.BigDecimal
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.animation.FloatingActionIconAnimation
import com.arudo.catatube.utils.PosterContainer
import com.arudo.catatube.utils.ToastMessage
import com.arudo.catatube.viewmodel.ViewModelFactory
import com.arudo.catatube.vo.Status
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
                        PosterContainer(null, this, it.data?.posterPath, imgShow)
                        imgBackground.tag = it.data?.posterPath
                        PosterContainer(null, this, it.data?.posterPath, imgBackground)
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
                        ToastMessage(this, resources.getString(R.string.errorMessage))
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
                ToastMessage(it.context, getString(R.string.addedMovieFavorite, nameMovie))
            } else {
                detailMovieViewModel.deleteFavoriteMovie(movieId)
                ToastMessage(it.context, getString(R.string.removeMovieFavorite, nameMovie))
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