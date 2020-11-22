package com.arudo.catatube.ui.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.arudo.catatube.R
import com.arudo.catatube.utils.ReceiverEvent
import com.arudo.catatube.utils.SortUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class FavoriteActivity : AppCompatActivity() {
    private var sort: String = ""

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.newest -> sort = SortUtils.newest
            R.id.oldest -> sort = SortUtils.oldest
        }
        EventBus.getDefault().postSticky(ReceiverEvent(sort))
        val favoriteSectionsPagerAdapter = FavoriteSectionsPagerAdapter(supportFragmentManager)
        favoriteSectionsPagerAdapter.addFragmentData(getString(R.string.movie))
        favoriteSectionsPagerAdapter.addFragmentData(getString(R.string.tv_show))
        viewPager.adapter = favoriteSectionsPagerAdapter
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }
}