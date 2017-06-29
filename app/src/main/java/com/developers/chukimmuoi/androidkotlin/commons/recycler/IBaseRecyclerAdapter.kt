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
internal interface IBaseRecyclerAdapter<T> {

    fun insertItem(position: Int, item: T, isScroll: Boolean = false)

    fun insertItem(positionStart: Int, itemList: List<T>, isScroll: Boolean = false)

    fun updateItem(position: Int, isScroll: Boolean = false)

    fun updateItem(positionStart: Int, itemCount: Int, isScroll: Boolean = false)

    fun removeItem(position: Int, isScroll: Boolean = false)

    fun removeItem(positionStart: Int, itemCount: Int, isScroll: Boolean = false)

    fun movedItem(fromPosition: Int, toPosition: Int, isScroll: Boolean = false)

    fun reloadAll(isScroll: Boolean = false)
}