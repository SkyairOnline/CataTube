package com.arudo.catatube.ui.detail.tv

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.activity_detail_television.*

class DetailTelevisionActivity : AppCompatActivity() {
    private lateinit var detailTelevisionViewModel: DetailTelevisionViewModel
    private lateinit var favoriteBtn: FloatingActionButton
    private lateinit var floatingActionIconAnimation: FloatingActionIconAnimation
    private var indexFavorite: Boolean = false
    private var nameTelevision: String = ""

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_television)
        supportActionBar?.title = getString(R.string.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar.visibility = View.VISIBLE
        layoutDetailConstraint.visibility = View.GONE
        detailTelevisionViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        ).get(
            DetailTelevisionViewModel::class.java
        )
        val televisionId = intent.extras?.getInt(EXTRA_DETAIL) ?: return
        favoriteBtn = findViewById(R.id.favoriteBtn)
        floatingActionIconAnimation = FloatingActionIconAnimation(this)
        floatingActionIconAnimation.floatingActionButton = favoriteBtn
        detailTelevisionViewModel.setDetailTelevision(televisionId)
        detailTelevisionViewModel.getDetailTelevision().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        layoutDetailConstraint.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        nameTelevision = it.data?.name.toString()
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
                        txtTitle.text = it.data?.name
                        txtSubTitle.text = getString(
                            R.string.txtSubtitleTelevision,
                            it.data?.firstAirDate
                        )
                        txtRating.text =
                            getString(R.string.txtRating, it.data?.voteAverage?.times(10))
                        txtEpisodeSeason.text =
                            getString(
                                R.string.seasonEpisodeNumber,
                                it.data?.season,
                                it.data?.episode
                            )
                        txtOverview.text = it.data?.overview
                        txtStatus.text = it.data?.status
                        txtType.text = it.data?.type
                        progressBar.visibility = View.GONE
                        layoutDetailConstraint.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        layoutDetailConstraint.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "There is an error. Please check the internet or contact the system administrator",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
        detailTelevisionViewModel.getFavoriteTelevision(televisionId).observe(this, {
            if (it != null) {
                indexFavorite = it.id == televisionId
            }
        })
        floatingActionIconAnimation.icon(indexFavorite)
        favoriteBtn.setOnClickListener {
            indexFavorite = !indexFavorite
            floatingActionIconAnimation.icon(indexFavorite)
            if (indexFavorite) {
                detailTelevisionViewModel.setFavoriteTelevision(televisionId)
                Toast.makeText(
                    this,
                    "You just added $nameTelevision from your Movie Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                detailTelevisionViewModel.deleteFavoriteTelevision(televisionId)
                Toast.makeText(
                    this,
                    "You just removed $nameTelevision from your Movie Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}