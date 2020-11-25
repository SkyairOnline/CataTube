package com.arudo.catatube.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.utils.SortUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TvFavoriteViewModel(
    private val cataTubeRepository: CataTubeRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    fun getFavoriteTelevisionList(filter: String): LiveData<PagedList<TVEntity>> =
        if (filter == SortUtils.newest) {
            cataTubeRepository.getFavoriteTelevisionListNewest()
        } else {
            cataTubeRepository.getFavoriteTelevisionListOldest()
        }

    fun deleteFavoriteTelevision(tvEntity: TVEntity) = viewModelScope.launch(defaultDispatcher) {
        cataTubeRepository.deleteTelevisionFavorite(tvEntity.id)
    }
}