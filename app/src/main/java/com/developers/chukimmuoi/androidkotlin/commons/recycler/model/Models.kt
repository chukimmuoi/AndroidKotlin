package com.developers.chukimmuoi.androidkotlin.commons.recycler.model

import com.developers.chukimmuoi.androidkotlin.commons.adapter.ItemAdapter
import com.developers.chukimmuoi.androidkotlin.constants.Constants

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/27/17.
 */

class LoadMoreCircleItem : ItemAdapter {
    override fun getViewType(): Int = Constants.VIEW_PROGRESS
}