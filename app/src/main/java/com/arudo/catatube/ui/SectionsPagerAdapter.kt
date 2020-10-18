package com.arudo.catatube.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arudo.catatube.ui.movietv.MovieTvFragment

class SectionsPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentDataList = ArrayList<String>()
    private val fragmentTitleList = ArrayList<String>()

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int {
        return fragmentDataList.size
    }

    override fun getItem(position: Int): Fragment {
        return MovieTvFragment.newInstance(fragmentDataList[position])
    }

    fun addFragmentData(data: String, title: String) {
        fragmentDataList.add(data)
        fragmentTitleList.add(title)
    }

}