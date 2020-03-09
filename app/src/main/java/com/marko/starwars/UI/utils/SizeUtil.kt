package com.marko.starwars.UI.utils

import android.content.res.Resources
import android.util.TypedValue

class SizeUtil {

    companion object SizeUtil {
        fun dpToPx(dp: Float,res: Resources): Float{
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                res.displayMetrics)
        }
    }

}