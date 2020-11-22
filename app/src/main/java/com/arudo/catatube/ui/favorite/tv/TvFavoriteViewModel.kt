package com.arudo.catatube.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TVEntity
import kotlinx.coroutines.launch

class TvFavoriteViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getFavoriteTelevisionList(filter: String): LiveData<PagedList<TVEntity>> =
        cataTubeRepository.getFavoriteTelevisionList(filter)

    fun deleteFavoriteTelevision(tvEntity: TVEntity) = viewModelScope.launch {
        cataTubeRepository.deleteTelevisionFavorite(tvEntity.id)
    }
}