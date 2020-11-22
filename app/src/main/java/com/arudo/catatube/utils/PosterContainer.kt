package com.arudo.catatube.utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.arudo.catatube.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun PosterContainer(
    context: Context? = null,
    activity: Activity? = null,
    posterPath: String?,
    view: ImageView
) {
    if (context != null) {
        Glide.with(context).load(context.getString(R.string.photo, posterPath))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .error(R.drawable.ic_broken_image)
            )
            .into(view)
    } else if (activity != null) {
        Glide.with(activity).load(activity.getString(R.string.photo, posterPath))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_image_loading)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .error(R.drawable.ic_broken_image)
            )
            .into(view)
    }
}