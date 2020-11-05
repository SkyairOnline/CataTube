package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.arudo.catatube.data.source.remote.AppExcecutors
import com.arudo.catatube.data.source.remote.response.ApiEmptyResponse
import com.arudo.catatube.data.source.remote.response.ApiErrorResponse
import com.arudo.catatube.data.source.remote.response.ApiResponse
import com.arudo.catatube.data.source.remote.response.ApiSuccessResponse
import com.arudo.catatube.data.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExcecutors: AppExcecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val databaseSource = loadFromDatabase()

        result.addSource(databaseSource) {
            result.removeSource(databaseSource)
            if (shouldFetch((it))) {
                fetchFromNetwork(databaseSource)
            } else {
                result.addSource(databaseSource) { new ->
                    result.value = Resource.success(new)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDatabase(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    fun asLiveData(): LiveData<Resource<ResultType>> = result

    private fun fetchFromNetwork(databaseSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(databaseSource) { new ->
            result.value = Resource.loading(new)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(databaseSource)
            when (response) {
                is ApiSuccessResponse -> appExcecutors.diskIO().execute {
                    response.body?.let { saveCallResult(it) }
                    appExcecutors.mainThread().execute {
                        result.addSource(loadFromDatabase()) {
                            result.value = Resource.success(it)
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExcecutors.mainThread().execute {
                        result.addSource(loadFromDatabase()) {
                            result.value = Resource.success(it)
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(databaseSource) {
                        result.value = Resource.error(response.errorMessage, it)
                    }
                }
            }
        }
    }


}