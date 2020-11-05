package com.arudo.catatube.ui.detail.tv

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.data.vo.Status
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

        detailTelevisionViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        ).get(
            DetailTelevisionViewModel::class.java
        )
        val televisionId = intent.extras?.getInt(EXTRA_DETAIL) ?: return
        detailTelevisionViewModel.setDetailTelevision(televisionId)
        detailTelevisionViewModel.getDetailTelevision().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        layoutDetailConstraint.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        layoutDetailConstraint.visibility = View.VISIBLE
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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}