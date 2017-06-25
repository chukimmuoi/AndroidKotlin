package com.developers.chukimmuoi.androidkotlin.ui.custom.swipe

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import com.developers.chukimmuoi.androidkotlin.R

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/23/17.
 */

class BaseSwipeRefreshLayout : SwipeRefreshLayout {

    constructor(context: Context) : super(context) {
        constructor()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        constructor()
    }

    private fun constructor() {
        setColorSchemeResources(R.color.colorSwipeBlue, R.color.colorSwipeGreen,
                R.color.colorSwipeOrange, R.color.colorSwipeRed)
    }
}