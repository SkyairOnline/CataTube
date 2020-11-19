package com.arudo.catatube.ui.detail.tv

import androidx.lifecycle.*
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.FavoriteTelevisionEntity
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.vo.Resource
import kotlinx.coroutines.launch

class DetailTelevisionViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private val televisionId = MutableLiveData<Int>()

    fun setDetailTelevision(televisionId: Int) {
        this.televisionId.value = televisionId
    }

    fun getDetailTelevision(): LiveData<Resource<TVEntity>> =
        Transformations.switchMap(televisionId) {
            cataTubeRepository.getTelevisionData(it)
        }

    fun getFavoriteTelevision(televisionId: Int): LiveData<FavoriteTelevisionEntity> =
        cataTubeRepository.getFavoriteTelevision(televisionId)

    fun setFavoriteTelevision(televisionId: Int) = viewModelScope.launch {
        cataTubeRepository.insertTelevisionFavorite(televisionId)
    }

    fun deleteFavoriteTelevision(televisionId: Int) = viewModelScope.launch {
        cataTubeRepository.deleteTelevisionFavorite(televisionId)
    }
}