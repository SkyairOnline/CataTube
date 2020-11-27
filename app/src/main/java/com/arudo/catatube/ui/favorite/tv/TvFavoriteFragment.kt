package com.arudo.catatube.ui.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arudo.catatube.R
import com.arudo.catatube.utils.ReceiverEvent
import com.arudo.catatube.utils.SortUtils
import com.arudo.catatube.utils.ToastMessage
import com.arudo.catatube.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_favorite.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TvFavoriteFragment : Fragment() {
    private lateinit var tvFavoriteViewModel: TvFavoriteViewModel
    private lateinit var tvFavoriteAdapter: TvFavoriteAdapter
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
        rvFavoriteTelevision.visibility = View.GONE
        notificationNothingInScreen.visibility = View.GONE
        tvFavoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        ).get(
            TvFavoriteViewModel::class.java
        )
        tvFavoriteAdapter = TvFavoriteAdapter()
        rvFavoriteTelevision.adapter = tvFavoriteAdapter
        itemTouchHelper.attachToRecyclerView(rvFavoriteTelevision)
        tvFavoriteViewModel.getFavoriteTelevisionList(sortedFix).observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.size > 0) {
                    tvFavoriteAdapter.submitList(it)
                    tvFavoriteAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                    rvFavoriteTelevision.visibility = View.VISIBLE
                    notificationNothingInScreen.visibility = View.GONE
                } else {
                    notificationNothingInScreen.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    rvFavoriteTelevision.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_favorite, container, false)
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
                val favoriteTelevisionEntity = tvFavoriteAdapter.getSwipedData(swipedPosition)
                ToastMessage(
                    context,
                    getString(R.string.removeTelevisionFavorite, favoriteTelevisionEntity?.name)
                )
                favoriteTelevisionEntity?.let { tvFavoriteViewModel.deleteFavoriteTelevision(it) }
            }
        }
    })

}