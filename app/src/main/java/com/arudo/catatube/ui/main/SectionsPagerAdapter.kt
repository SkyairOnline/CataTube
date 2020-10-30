package com.arudo.catatube.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arudo.catatube.ui.main.movie.MovieFragment
import com.arudo.catatube.ui.main.tv.TvFragment

class SectionsPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentTitleList = ArrayList<String>()

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int {
        return fragmentTitleList.size
    }

    override fun getItem(position: Int): Fragment {
        return when {
            (position == 0) -> MovieFragment()
            (position == 1) -> TvFragment()
            else -> Fragment()
        }
    }

    fun addFragmentData(title: String) {
        fragmentTitleList.add(title)
    }

}