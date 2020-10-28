package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arudo.catatube.data.source.local.entity.MovieTVEntity
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.data.source.remote.response.MovieListResponse
import com.arudo.catatube.data.source.remote.response.MovieResponse
import com.arudo.catatube.data.source.remote.response.TVListResponse
import com.arudo.catatube.data.source.remote.response.TVResponse

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

    override fun getMoviesList(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>> {
        val movieListResult = MutableLiveData<ArrayList<MovieTVEntity>>()
        remoteDataSource.getMoviesList(
            object : RemoteDataSource.LoadMovieListCallback {
                override fun onAllMoviesListReceived(movieListResponse: MovieListResponse) {
                    val movieTelevisionList = ArrayList<MovieTVEntity>()
                    for (response in movieListResponse.results!!) {
                        movieTelevisionList.add(
                            MovieTVEntity(
                                id = response.id,
                                image = response.posterPath,
                                title = response.title,
                                overview = response.overview,
                                status = dataStringKey
                            )
                        )
                    }
                    movieListResult.postValue(movieTelevisionList)
                }

            })

        return movieListResult
    }

    override fun getTelevisionsList(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>> {
        val televisionListResult = MutableLiveData<ArrayList<MovieTVEntity>>()
        remoteDataSource.getTelevisionsList(
            object : RemoteDataSource.LoadTelevisionListCallBack {
                override fun onAllTelevisionsListReceived(televisionListResponse: TVListResponse) {
                    val movieTelevisionList = ArrayList<MovieTVEntity>()
                    for (response in televisionListResponse.results!!) {
                        movieTelevisionList.add(
                            MovieTVEntity(
                                id = response.id,
                                image = response.posterPath,
                                title = response.name,
                                overview = response.overview,
                                status = dataStringKey
                            )
                        )
                    }
                    televisionListResult.postValue(movieTelevisionList)
                }

            })

        return televisionListResult
    }

    override fun getMovieData(
        movieId: Int,
        movieType: String
    ): LiveData<MovieTVEntity> {
        val movieTelevisionDataResult = MutableLiveData<MovieTVEntity>()
        remoteDataSource.getMovieData(
            movieId,
            object : RemoteDataSource.LoadMovieDataCallBack {
                override fun onMovieDataReceived(movieResponse: MovieResponse) {
                    movieTelevisionDataResult.postValue(
                        MovieTVEntity(
                            id = movieResponse.id,
                            image = movieResponse.posterPath,
                            title = movieResponse.title,
                            date = movieResponse.releaseDate,
                            duration = movieResponse.runtime,
                            rating = movieResponse.voteAverage,
                            quote = movieResponse.tagline,
                            overview = movieResponse.overview,
                            status = movieResponse.status,
                            type = movieType,
                        )
                    )
                }
            })
        return movieTelevisionDataResult
    }

    override fun getTelevisionData(
        televisionId: Int,
        televisionType: String
    ): LiveData<MovieTVEntity> {
        val movieTelevisionDataResult = MutableLiveData<MovieTVEntity>()
        remoteDataSource.getTelevisionData(
            televisionId,
            object : RemoteDataSource.LoadTelevisionDataCallBack {
                override fun onTelevisionDataReceived(televisionResponse: TVResponse) {
                    movieTelevisionDataResult.postValue(
                        MovieTVEntity(
                            id = televisionResponse.id,
                            image = televisionResponse.posterPath,
                            title = televisionResponse.name,
                            date = televisionResponse.firstAirDate,
                            duration = televisionResponse.episodeRunTime?.get(0),
                            rating = televisionResponse.voteAverage,
                            overview = televisionResponse.overview,
                            status = televisionResponse.status,
                            type = televisionType,
                        )
                    )
                }
            })
        return movieTelevisionDataResult
    }
}