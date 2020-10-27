package com.arudo.catatube.ui.detail

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
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = getString(R.string.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar.visibility = View.VISIBLE
        layoutDetailConstraint.visibility = View.GONE
        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getViewModelFactory()
        ).get(
            DetailViewModel::class.java
        )
        val movieTelevisionId = intent.extras?.getString(EXTRA_DETAIL) ?: return
        val movieTelevisionType = intent.extras?.getString(EXTRA_TYPE) ?: return
        detailViewModel.setDetailMovieTelevision(movieTelevisionId, movieTelevisionType)
        detailViewModel.getDetailMovieTelevision().observe(this, {
            if (it != null) {
                progressBar.visibility = View.GONE
                layoutDetailConstraint.visibility = View.VISIBLE
                imgShow.tag = it.image
                Glide.with(this)
                    .load(it.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                            .transform(CenterCrop(), RoundedCorners(10))
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgShow)
                imgBackground.tag = it.image
                Glide.with(this)
                    .load(it.image)
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
                        R.string.txtSubtitle, it.year, it.genre,
                        it.duration?.div(60), it.duration?.rem(60)
                    )
                txtRating.text = getString(R.string.txtRating, it.rating?.times(10))
                txtQuote.text = it.quote
                txtOverview.text = it.overview
                txtStatus.text = it.status
                txtLanguage.text = it.language
                txtBudget.text = getString(R.string.txtBudgetRevenue, it.budget)
                txtRevenue.text = getString(R.string.txtBudgetRevenue, it.revenue)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}