package com.marko.starwars.ui.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

class SizeUtil(val context: Context) {

        fun dpToPx(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.resources.displayMetrics
            )
        }

}