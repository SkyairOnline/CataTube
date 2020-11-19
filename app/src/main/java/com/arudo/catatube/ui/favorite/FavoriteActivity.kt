package com.arudo.catatube.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arudo.catatube.R
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        supportActionBar?.title = getString(R.string.favorite_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val favoriteSectionsPagerAdapter = FavoriteSectionsPagerAdapter(supportFragmentManager)
        favoriteSectionsPagerAdapter.addFragmentData(getString(R.string.movie))
        favoriteSectionsPagerAdapter.addFragmentData(getString(R.string.tv_show))
        viewPager.adapter = favoriteSectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}