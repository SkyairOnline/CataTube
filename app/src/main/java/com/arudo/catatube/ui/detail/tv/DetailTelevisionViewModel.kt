package com.arudo.catatube.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.remote.response.TVResponse
import kotlin.properties.Delegates

class DetailTelevisionViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private var televisionId by Delegates.notNull<Int>()

    fun setDetailTelevision(televisionId: Int) {
        this.televisionId = televisionId
    }

    fun getDetailTelevision(): LiveData<TVResponse> =
        cataTubeRepository.getTelevisionData(televisionId)

}