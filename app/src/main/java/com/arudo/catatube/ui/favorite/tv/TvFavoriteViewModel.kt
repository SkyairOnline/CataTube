package com.arudo.catatube.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TVEntity

class TvFavoriteViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getFavoriteTelevisionList(): LiveData<List<TVEntity>> =
        cataTubeRepository.getFavoriteTelevisionList()
}