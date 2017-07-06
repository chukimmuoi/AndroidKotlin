package com.developers.chukimmuoi.androidkotlin.commons.recycler

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.developers.chukimmuoi.androidkotlin.commons.adapter.ItemAdapter
import com.developers.chukimmuoi.androidkotlin.commons.adapter.delegate.AdapterDelegatesManager
import com.developers.chukimmuoi.androidkotlin.commons.adapter.delegate.LoadMoreCircleDelegate

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/23/17.
 */

open class BaseRecyclerAdapter(context: Activity,
                               recyclerView: BaseRecyclerView,
                               list: MutableList<ItemAdapter>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), IBaseRecyclerAdapter<ItemAdapter> {

    private val TAG = BaseRecyclerAdapter::class.java.simpleName

    protected var mDelegateManager: AdapterDelegatesManager<List<ItemAdapter>> = AdapterDelegatesManager()

    protected val mContext        : Activity = context

    private val mRecyclerView     : BaseRecyclerView = recyclerView

    private val mList             : MutableList<ItemAdapter> = list

    init {
        mDelegateManager.addDelegate(LoadMoreCircleDelegate(mContext))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return mDelegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mDelegateManager.onBindViewHolder(mList, position, holder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>?) {
        mDelegateManager.onBindViewHolder(mList, position, holder, payloads)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mDelegateManager.getItemViewType(mList, position)
    }

    /**
     * Add item
     *
     * @param position getItemCount or mList.size()
     * @param item     new object
     * @param isScroll
     */
    override fun insertItem(position: Int, item: ItemAdapter, isScroll: Boolean) {
        if (position >= 0) {
            mList.add(position, item)

            mRecyclerView.post { notifyItemInserted(position) }

            if (isScroll) mRecyclerView.scrollToPosition(position)
        }
    }

    /**
     * Add list item
     *
     * @param positionStart getItemCount or mList.size()
     * @param itemList      list object
     * @param isScroll
     */
    override fun insertItem(positionStart: Int, itemList: List<ItemAdapter>, isScroll: Boolean) {
        if (positionStart >= 0) {
            val itemCount: Int = itemList.size
            if (itemCount > 0) {
                mList.addAll(positionStart, itemList)

                mRecyclerView.post { notifyItemRangeInserted(positionStart, itemCount) }

                if (isScroll) mRecyclerView.scrollToPosition(positionStart)
            }
        }
    }

    /**
     * Update item
     *
     * @param position all in 0 <= position < getItemCount or mList.size()
     * @param isScroll
     */
    override fun updateItem(position: Int, isScroll: Boolean) {
        if (position in 0..itemCount - 1) {
            mRecyclerView.post { notifyItemChanged(position) }

            if (isScroll) mRecyclerView.scrollToPosition(position)
        }
    }

    /**
     * Update list item
     *
     * @param positionStart all in 0 <= position < getItemCount or mList.size()
     * @param itemCount     size of list update. eg: list.size()
     * @param isScroll
     */
    override fun updateItem(positionStart: Int, itemCount: Int, isScroll: Boolean) {
        if (positionStart >= 0 && itemCount > 0 && (positionStart + itemCount) < itemCount) {
            mRecyclerView.post { notifyItemRangeChanged(positionStart, itemCount) }

            if (isScroll) mRecyclerView.scrollToPosition(positionStart)
        }
    }

    /**
     * Remove item
     *
     * @param position all in 0 <= position < getItemCount or mList.size()
     * @param isScroll
     */
    override fun removeItem(position: Int, isScroll: Boolean) {
        if (position in 0..itemCount - 1) {
            mList.removeAt(position)

            mRecyclerView.post { notifyItemRemoved(position) }

            if (isScroll) {
                val positionNew: Int = position - 1
                mRecyclerView.scrollToPosition(if (positionNew >= 0) positionNew else 0)
            }
        }
    }

    /**
     * Remove list item
     *
     * @param positionStart all in 0 <= position < getItemCount or mList.size()
     * @param itemCount     size of list remove. eg: list.size()
     * @param isScroll
     */
    override fun removeItem(positionStart: Int, itemCount: Int, isScroll: Boolean) {
        if (positionStart >= 0 && itemCount > 0 && (positionStart + itemCount) < getItemCount()) {
            for (i in 0..itemCount - 1) {
                mList.removeAt(positionStart)
            }

            mRecyclerView.post { notifyItemRangeRemoved(positionStart, itemCount) }

            if (isScroll) {
                val positionNew = positionStart - 1
                mRecyclerView.scrollToPosition(if (positionNew >= 0) positionNew else 0)
            }
        }
    }

    /**
     * Moved item
     *
     * @param fromPosition
     * @param toPosition
     * @param isScroll
     */
    override fun movedItem(fromPosition: Int, toPosition: Int, isScroll: Boolean) {
        if (fromPosition in 0..(itemCount - 1)
                && toPosition >= 0 && toPosition < itemCount
                && fromPosition != toPosition) {
            val obj = mList[fromPosition]
            mList.removeAt(fromPosition)
            mList.add(toPosition, obj)

            mRecyclerView.post { notifyItemMoved(fromPosition, toPosition) }

            if (isScroll) mRecyclerView.scrollToPosition(toPosition)
        }
    }

    /**
     * Reload all adapter
     *
     * @param isScroll true: scroll to position 0, false: not scroll
     */
    override fun reloadAll(isScroll: Boolean) {
        mRecyclerView.post { notifyDataSetChanged() }

        if (isScroll) mRecyclerView.scrollToPosition(0)
    }
}