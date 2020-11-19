package com.arudo.catatube.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_favorite.*

class MovieFavoriteFragment : Fragment() {
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
        rvFavoriteMovie.visibility = View.GONE
        movieFavoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        ).get(
            MovieFavoriteViewModel::class.java
        )
        val movieFavoriteAdapter = MovieFavoriteAdapter()
        rvFavoriteMovie.adapter = movieFavoriteAdapter
        movieFavoriteViewModel.getFavoriteMovieList().observe(viewLifecycleOwner, {
            if (it != null) {
                progressBar.visibility = View.GONE
                rvFavoriteMovie.visibility = View.VISIBLE
                movieFavoriteAdapter.setData(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }
}