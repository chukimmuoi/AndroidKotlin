package com.developers.chukimmuoi.androidkotlin.commons.recycler

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/23/17.
 */
internal interface IBaseRecyclerView {
    fun initLayoutManager(typeLayout: Int, spanCount: Int = 0,
                          isHorizontal: Boolean = false, isReverse: Boolean = false)

    fun initLinearLayoutManager()

    fun initGridLayoutManager(spanCount: Int)

    fun initStaggeredGridLayoutManager(spanCount: Int)

    fun setLinearSnapHelper(typeGravity: Int, isSnapPager: Boolean = false)
}