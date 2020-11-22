package com.arudo.catatube.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.arudo.catatube.vo.Resource
import com.arudo.catatube.vo.Status
import kotlinx.coroutines.Dispatchers

fun <ResultType, RequestType> NetworkBoundResource(
    loadFromDatabase: () -> LiveData<ResultType>,
    networkCall: suspend () -> Resource<RequestType>,
    saveCallResult: suspend (RequestType) -> Unit
): LiveData<Resource<ResultType>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val dataSource = loadFromDatabase.invoke().map {
            Resource.success(it)
        }
        emitSource(dataSource)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            responseStatus.data?.let { saveCallResult(it) }
        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(responseStatus.message))
            emitSource(dataSource)
        }
    }
}