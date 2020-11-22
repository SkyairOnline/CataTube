package com.arudo.catatube.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.utils.ReceiverEvent
import com.arudo.catatube.utils.SortUtils
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_favorite.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MovieFavoriteFragment : Fragment() {
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    private var sortedFix = SortUtils.newest

    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessage(event: ReceiverEvent) {
        sortedFix = event.message
        EventBus.getDefault().removeAllStickyEvents()
    }

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
        movieFavoriteAdapter = MovieFavoriteAdapter()
        rvFavoriteMovie.adapter = movieFavoriteAdapter
        itemTouchHelper.attachToRecyclerView(rvFavoriteMovie)
        movieFavoriteViewModel.getFavoriteMovieList(sortedFix).observe(viewLifecycleOwner, {
            if (it != null) {
                movieFavoriteAdapter.submitList(it)
                movieFavoriteAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                rvFavoriteMovie.visibility = View.VISIBLE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val favoriteMovieEntity = movieFavoriteAdapter.getSwipedData(swipedPosition)
                Toast.makeText(
                    context,
                    "You just removed ${favoriteMovieEntity?.title} from your Movie Favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteMovieEntity?.let { movieFavoriteViewModel.deleteFavoriteMovie(it) }
            }
        }
    })
}