package com.arudo.catatube.ui.main.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.R
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment() {
    private lateinit var tvViewModel: TvViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
        rvTelevision.visibility = View.GONE
        tvViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        ).get(
            TvViewModel::class.java
        )
        val televisionAdapter = TvAdapter()
        rvTelevision.adapter = televisionAdapter
        tvViewModel.getTelevisionsList().observe(viewLifecycleOwner, {
            if (it != null) {
                progressBar.visibility = View.GONE
                rvTelevision.visibility = View.VISIBLE
                televisionAdapter.setData(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }
}