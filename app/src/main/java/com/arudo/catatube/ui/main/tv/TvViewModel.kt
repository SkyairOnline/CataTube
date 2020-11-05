package com.arudo.catatube.ui.main.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.data.vo.Resource

class TvViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getTelevisionsList(): LiveData<Resource<List<TelevisionResultsItem>>> =
        cataTubeRepository.getTelevisionsList()
}