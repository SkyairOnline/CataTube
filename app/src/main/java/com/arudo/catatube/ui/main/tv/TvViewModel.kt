package com.arudo.catatube.ui.main.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem

class TvViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getTelevisionsList(): LiveData<ArrayList<TelevisionResultsItem>> =
        cataTubeRepository.getTelevisionsList()
}