package com.arudo.catatube.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arudo.catatube.ui.favorite.movie.MovieFavoriteFragment
import com.arudo.catatube.ui.favorite.tv.TvFavoriteFragment

class FavoriteSectionsPagerAdapter(fragmentManager: FragmentManager) :
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
            (position == 0) -> MovieFavoriteFragment()
            (position == 1) -> TvFavoriteFragment()
            else -> Fragment()
        }
    }

    fun addFragmentData(title: String) {
        fragmentTitleList.add(title)
    }

}