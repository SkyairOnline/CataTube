package com.arudo.catatube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        sectionsPagerAdapter.addFragmentData("Movie", "Movie")
        sectionsPagerAdapter.addFragmentData("TV", "TV Show")
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        supportActionBar?.elevation = 0f
    }
}