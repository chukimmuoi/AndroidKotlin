@file:JvmName("SizeUtil")

package com.developers.chukimmuoi.androidkotlin.utils

import android.content.Context
import android.util.DisplayMetrics

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/24/17.
 *

 *
 * @see {http://stackoverflow.com/questions/4605527/converting-pixels-to-dp}
 */

/**
 * This method converts dp unit to equivalent pixels, depending on device density.

 * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
 * *
 * @param context Context to get resources and device specific display metrics
 * *
 * @return A float value to represent px equivalent to dp depending on device density
 */
fun Float.convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.getResources()
    val metrics = resources.getDisplayMetrics()
    val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    return px
}

/**
 * This method converts device specific pixels to density independent pixels.

 * @param px      A value in px (pixels) unit. Which we need to convert into db
 * *
 * @param context Context to get resources and device specific display metrics
 * *
 * @return A float value to represent dp equivalent to px value
 */
fun Float.convertPixelsToDp(px: Float, context: Context): Float {
    val resources = context.getResources()
    val metrics = resources.getDisplayMetrics()
    val dp = px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    return dp
}
