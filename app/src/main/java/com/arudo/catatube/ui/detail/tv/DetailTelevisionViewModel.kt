package com.arudo.catatube.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.data.vo.Resource

class DetailTelevisionViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private val televisionId = MutableLiveData<Int>()

    fun setDetailTelevision(televisionId: Int) {
        this.televisionId.value = televisionId
    }

    fun getDetailTelevision(): LiveData<Resource<TVEntity>> =
        Transformations.switchMap(televisionId) {
            cataTubeRepository.getTelevisionData(it)
        }

}