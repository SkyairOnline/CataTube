package com.arudo.catatube.animation

import android.content.Context
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.arudo.catatube.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FloatingActionIconAnimation(context: Context) {
    var floatingActionButton: FloatingActionButton? = null
    private val favoriteBorder = AnimatedVectorDrawableCompat.create(
        context,
        R.drawable.ic_favorite_border
    )
    private val favorite = AnimatedVectorDrawableCompat.create(
        context,
        R.drawable.ic_favorite_black
    )

    fun icon(isState: Boolean) {
        val nextAnimation = if (isState) favorite else favoriteBorder
        floatingActionButton?.setImageDrawable(nextAnimation)
    }
}