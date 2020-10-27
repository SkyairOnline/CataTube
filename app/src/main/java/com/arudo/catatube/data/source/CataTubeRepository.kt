package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arudo.catatube.data.source.local.entity.MovieTVEntity
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.data.source.remote.response.MovieTVResponse

class CataTubeRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CataTubeDataSource {

    companion object {
        @Volatile
        private var cataTubeRepository: CataTubeRepository? = null

        fun getCataTubeRepository(remoteData: RemoteDataSource): CataTubeRepository =
            cataTubeRepository ?: synchronized(this) {
                cataTubeRepository ?: CataTubeRepository(remoteData)
            }
    }

    override fun getMoviesTelevisionsList(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>> {
        val movieTelevisionListResult = MutableLiveData<ArrayList<MovieTVEntity>>()
        remoteDataSource.getMoviesTelevisionsList(
            dataStringKey,
            object : RemoteDataSource.LoadMovieTelevisionListCallback {
                override fun onAllMoviesTelevisionsListReceived(movieTelevisionListResponse: ArrayList<MovieTVResponse>) {
                    val movieTelevisionList = ArrayList<MovieTVEntity>()
                    for (response in movieTelevisionListResponse) {
                        movieTelevisionList.add(
                            MovieTVEntity(
                                response.id,
                                response.image,
                                response.title,
                                response.year,
                                response.genre,
                                response.duration,
                                response.rating,
                                response.quote,
                                response.overview,
                                response.status,
                                response.language,
                                response.budget,
                                response.revenue,
                                response.type,
                            )
                        )
                    }
                    movieTelevisionListResult.postValue(movieTelevisionList)
                }

            })

        return movieTelevisionListResult
    }

    override fun getMovieTelevisionData(
        movieTelevisionId: String,
        movieTelevisionType: String
    ): LiveData<MovieTVEntity> {
        val movieTelevisionDataResult = MutableLiveData<MovieTVEntity>()
        remoteDataSource.getMovieTelevisionData(
            movieTelevisionId,
            movieTelevisionType,
            object : RemoteDataSource.LoadMovieTelevisionDataCallback {
                override fun onMoviesTelevisionDataReceived(movieTelevisionDataResponse: MovieTVResponse) {
                    movieTelevisionDataResult.postValue(
                        MovieTVEntity(
                            movieTelevisionDataResponse.id,
                            movieTelevisionDataResponse.image,
                            movieTelevisionDataResponse.title,
                            movieTelevisionDataResponse.year,
                            movieTelevisionDataResponse.genre,
                            movieTelevisionDataResponse.duration,
                            movieTelevisionDataResponse.rating,
                            movieTelevisionDataResponse.quote,
                            movieTelevisionDataResponse.overview,
                            movieTelevisionDataResponse.status,
                            movieTelevisionDataResponse.language,
                            movieTelevisionDataResponse.budget,
                            movieTelevisionDataResponse.revenue,
                            movieTelevisionDataResponse.type
                        )
                    )
                }
            })
        return movieTelevisionDataResult
    }
}