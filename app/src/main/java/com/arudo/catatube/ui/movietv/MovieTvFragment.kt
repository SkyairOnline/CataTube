package com.arudo.catatube.ui.movietv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.data.MovieTVEntity
import kotlinx.android.synthetic.main.fragment_movie_tv.*

class MovieTvFragment : Fragment() {
    private lateinit var movieTvViewModel: MovieTvViewModel
    private var dataStringKey = ""
    private lateinit var items: ArrayList<MovieTVEntity>

    companion object {
        private const val dataString = ""
        fun newInstance(data: String): MovieTvFragment {
            val fragment = MovieTvFragment()
            val bundle = Bundle()
            bundle.putString(dataString, data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStringKey = arguments?.getString(dataString, "") ?: ""
        movieTvViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(
            MovieTvViewModel::class.java
        )
        items = ArrayList()
        if (dataStringKey == "Movie") {
            items = movieTvViewModel.getMovies()
        } else if (dataStringKey == "TV") {
            items = movieTvViewModel.getTelevisions()
        }
        val adapter = MovieTvAdapter()
        adapter.setData(items)
        rvMovieTV.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_tv, container, false)
    }
}