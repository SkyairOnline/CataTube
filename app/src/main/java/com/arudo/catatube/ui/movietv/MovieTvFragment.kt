package com.arudo.catatube.ui.movietv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_tv.*

class MovieTvFragment : Fragment() {
    private lateinit var movieTvViewModel: MovieTvViewModel
    private var dataStringKey = ""

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
        progressBar.visibility = View.VISIBLE
        rvMovieTV.visibility = View.GONE
        dataStringKey = arguments?.getString(dataString, "") ?: ""
        movieTvViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getViewModelFactory()
        ).get(
            MovieTvViewModel::class.java
        )
        val movieTvAdapter = MovieTvAdapter()
        rvMovieTV.adapter = movieTvAdapter
        movieTvViewModel.getMoviesTelevisions(dataStringKey)?.observe(viewLifecycleOwner, {
            if (it != null) {
                progressBar.visibility = View.GONE
                rvMovieTV.visibility = View.VISIBLE
                movieTvAdapter.setData(it)
                movieTvAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_tv, container, false)
    }
}