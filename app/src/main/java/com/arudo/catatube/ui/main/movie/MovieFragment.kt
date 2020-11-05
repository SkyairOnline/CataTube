package com.arudo.catatube.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.data.vo.Status
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
        rvMovie.visibility = View.GONE
        movieViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        ).get(
            MovieViewModel::class.java
        )
        val movieAdapter = MovieAdapter()
        rvMovie.adapter = movieAdapter
        movieViewModel.getMovieList().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        rvMovie.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        rvMovie.visibility = View.VISIBLE
                        it.data?.let { it1 -> movieAdapter.setData(it1) }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        rvMovie.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "There is an error. Please check the internet or contact the system administrator",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
}