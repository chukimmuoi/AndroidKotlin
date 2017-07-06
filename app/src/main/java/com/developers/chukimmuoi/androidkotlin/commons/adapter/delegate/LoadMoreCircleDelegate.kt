package com.developers.chukimmuoi.androidkotlin.commons.adapter.delegate

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.chukimmuoi.androidkotlin.R
import com.developers.chukimmuoi.androidkotlin.commons.adapter.ItemAdapter
import com.developers.chukimmuoi.androidkotlin.commons.adapter.LoadMoreCircleItem
import com.developers.chukimmuoi.androidkotlin.utils.inflate
import kotlinx.android.synthetic.main.item_recycler_circle_progress.view.*

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/27/17.
 */

class LoadMoreCircleDelegate(activity: Activity)
    : AdapterDelegate<List<ItemAdapter>>() {

    private val TAG = LoadMoreCircleDelegate::class.java.simpleName

    private val inflater: LayoutInflater = activity.layoutInflater

    override fun isForViewType(items: List<ItemAdapter>, position: Int): Boolean {
        return items[position] is LoadMoreCircleItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return LoadMoreViewHolder(parent)
    }

    override fun onBindViewHolder(items: List<ItemAdapter>,
                                  position: Int, holder: RecyclerView.ViewHolder,
                                  payloads: List<Any>) {
        val viewHolder = (holder as LoadMoreViewHolder)

        viewHolder.bin()

        //TODO: Load more full span (StaggeredGridLayoutManager).
        val layoutParams: ViewGroup.LayoutParams = holder.itemView.layoutParams
        if (layoutParams != null && layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }

    inner class LoadMoreViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.item_recycler_circle_progress)) {

        fun bin() {
            itemView.progress_circle.visibility = View.VISIBLE
        }
    }
}