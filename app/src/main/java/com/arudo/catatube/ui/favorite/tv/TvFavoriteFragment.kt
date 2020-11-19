package com.arudo.catatube.ui.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_favorite.*

class TvFavoriteFragment : Fragment() {
    private lateinit var tvFavoriteViewModel: TvFavoriteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
        rvFavoriteTelevision.visibility = View.GONE
        tvFavoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        ).get(
            TvFavoriteViewModel::class.java
        )
        val tvFavoriteAdapter = TvFavoriteAdapter()
        rvFavoriteTelevision.adapter = tvFavoriteAdapter
        tvFavoriteViewModel.getFavoriteTelevisionList().observe(viewLifecycleOwner, {
            if (it != null) {
                progressBar.visibility = View.GONE
                rvFavoriteTelevision.visibility = View.VISIBLE
                tvFavoriteAdapter.setData(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_favorite, container, false)
    }
}