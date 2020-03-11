package com.marko.starwars.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor() {
    companion object {
        @BindingAdapter("imageUrl", "imageWidthInDp", "imageHeightInDp")
        @JvmStatic
        fun loadImage(
            view: ImageView,
            imageUrl: String?,
            imageWidthInDp: Float,
            imageHeightInDp: Float
        ) {
            val imageWidthInPx = SizeUtil.dpToPx(view.context, imageWidthInDp).toInt()
            val imageHeightInPx = SizeUtil.dpToPx(view.context, imageHeightInDp).toInt()
            Picasso.get()
                .load(imageUrl)
                .resize(imageWidthInPx, imageHeightInPx)
                .into(view)
        }

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImageInWholeView(view: ImageView, imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }
}