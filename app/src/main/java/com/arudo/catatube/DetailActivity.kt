package com.arudo.catatube

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.arudo.catatube.data.MovieTVEntity
import com.bumptech.glide.Glide
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
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgShow)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}