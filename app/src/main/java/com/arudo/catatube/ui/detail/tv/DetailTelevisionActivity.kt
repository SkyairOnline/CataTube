package com.arudo.catatube.ui.detail.tv

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
import kotlinx.android.synthetic.main.activity_detail_television.*

class DetailTelevisionActivity : AppCompatActivity() {
    private lateinit var detailTelevisionViewModel: DetailTelevisionViewModel

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
            ViewModelFactory.getInstance()
        ).get(
            DetailTelevisionViewModel::class.java
        )
        val televisionId = intent.extras?.getInt(EXTRA_DETAIL) ?: return
        detailTelevisionViewModel.setDetailTelevision(televisionId)
        detailTelevisionViewModel.getDetailTelevision().observe(this, {
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
                txtTitle.text = it.name
                txtSubTitle.text = getString(
                    R.string.txtSubtitle,
                    it.firstAirDate,
                    it.episodeRunTime[0].div(60),
                    it.episodeRunTime[0].rem(60)
                )
                txtRating.text = getString(R.string.txtRating, it.voteAverage?.times(10))
                txtEpisodeSeason.text =
                    getString(R.string.seasonEpisodeNumber, it.season, it.episode)
                txtOverview.text = it.overview
                txtStatus.text = it.status
                txtType.text = it.type
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}