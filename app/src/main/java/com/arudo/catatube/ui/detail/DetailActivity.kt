package com.arudo.catatube.ui.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.arudo.catatube.R
import com.arudo.catatube.data.MovieTVEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LIST = "extra_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail CataTube"
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }
        val detail: MovieTVEntity = intent.getParcelableExtra(EXTRA_LIST)!!
        Glide.with(this)
                .load(detail.image)
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                                .transform(CenterCrop(), RoundedCorners(10))
                                .error(R.drawable.ic_broken_image)
                )
                .into(imgShow)
        Glide.with(this)
                .load(detail.image)
                .centerCrop()
                .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                                .transform(CenterCrop(), RoundedCorners(10))
                                .error(R.drawable.ic_broken_image)
                )
                .into(imgBackground)
        txtTitle.text = detail.title
        txtSubTitle.text = getString(R.string.txtSubtitle, detail.year, detail.genre, detail.duration)
        txtRating.text = getString(R.string.txtRating, detail.rating)
        txtQuote.text = detail.quote
        txtOverview.text = detail.overview
        txtStatus.text = detail.status
        txtLanguage.text = detail.language
        txtBudget.text = getString(R.string.txtBudget, detail.budget)
        txtRevenue.text = getString(R.string.txtRevenue, detail.revenue)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}