package com.marko.starwars.ui.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor() {
    companion object {
        @BindingAdapter("imageUrl", "imageWidthInDp", "imageHeightInDp", requireAll = false)
        @JvmStatic
        fun loadImage(
            view: ImageView,
            imageUrl: String?,
            imageWidthInDp: Float,
            imageHeightInDp: Float
        ) {
            if (isSizeProvided(imageWidthInDp, imageHeightInDp)) {
                val imageWidthInPx = convertDpToPx(view.context, imageWidthInDp)
                val imageHeightInPx = convertDpToPx(view.context, imageHeightInDp)
                loadImageWithSize(imageWidthInPx, imageHeightInPx, view, imageUrl)
            } else {
                loadImageWithoutSizeProvided(view, imageUrl)
            }
        }

        private fun convertDpToPx(context: Context, dp: Float): Int {
            return SizeUtil.dpToPx(context, dp).toInt()
        }

        private fun isSizeProvided(width: Float, height: Float): Boolean {
            return width > 0 && height > 0
        }

        private fun loadImageWithSize(width: Int, height: Int, view: ImageView, imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .resize(width, height)
                .into(view)
        }

        private fun loadImageWithoutSizeProvided(view: ImageView, imageUrl: String?) {
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }
}