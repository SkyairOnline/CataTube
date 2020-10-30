package com.arudo.catatube.ui.main.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.remote.response.TelevisionResultsItem

class TvViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getTelevisionsList(): LiveData<ArrayList<TelevisionResultsItem>> =
        cataTubeRepository.getTelevisionsList()
}