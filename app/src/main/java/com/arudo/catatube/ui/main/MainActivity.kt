package com.arudo.catatube.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arudo.catatube.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        sectionsPagerAdapter.addFragmentData(getString(R.string.movie), getString(R.string.movie))
        sectionsPagerAdapter.addFragmentData(
            getString(R.string.tv_show),
            getString(R.string.tv_show)
        )
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }
}