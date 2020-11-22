package com.arudo.catatube.ui.detail.tv

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.animation.FloatingActionIconAnimation
import com.arudo.catatube.utils.PosterContainer
import com.arudo.catatube.utils.ToastMessage
import com.arudo.catatube.viewmodel.ViewModelFactory
import com.arudo.catatube.vo.Status
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
                        PosterContainer(null, this, it.data?.posterPath, imgShow)
                        imgBackground.tag = it.data?.posterPath
                        PosterContainer(null, this, it.data?.posterPath, imgBackground)
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
                ToastMessage(
                    it.context,
                    getString(R.string.addedTelevisionFavorite, nameTelevision)
                )
            } else {
                detailTelevisionViewModel.deleteFavoriteTelevision(televisionId)
                ToastMessage(
                    it.context,
                    getString(R.string.removeTelevisionFavorite, nameTelevision)
                )
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}