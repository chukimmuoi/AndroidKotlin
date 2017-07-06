package com.developers.chukimmuoi.androidkotlin.listener.onclick

import android.view.View

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/29/17.
 */
interface OnItemClickListener {
    fun onItemClick(className: String, itemView: View, position: Int)
}