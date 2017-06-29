package com.developers.chukimmuoi.androidkotlin.commons.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.chukimmuoi.androidkotlin.R
import com.developers.chukimmuoi.androidkotlin.commons.recycler.model.LoadMoreObject
import com.developers.chukimmuoi.androidkotlin.constants.Constants
import com.developers.chukimmuoi.androidkotlin.utils.inflate
import kotlinx.android.synthetic.main.item_recycler_circle_progress.view.*

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/23/17.
 */

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(context: Context,
                                                                    recyclerView: BaseRecyclerView,
                                                                    list: MutableList<T>)
    : RecyclerView.Adapter<VH>(), IBaseRecyclerAdapter<T> {

    private val TAG = BaseRecyclerAdapter::class.java.simpleName

    protected val mContext   : Context = context

    private val mRecyclerView: BaseRecyclerView = recyclerView

    protected val mList      : MutableList<T> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH? {
        val context : Context        = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)

        var viewHolder: VH? = null

        if (viewType === Constants.VIEW_PROGRESS) {
            viewHolder = ProgressViewHolder(parent) as VH
        } else if (viewType === Constants.VIEW_ITEM) {
            val view = setLayout(inflater, parent)

            viewHolder = createViewHolder(view)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val type: Int = getItemViewType(position)

        if (type === Constants.VIEW_ITEM) {
            displayItem(holder, position)
        } else if (type === Constants.VIEW_PROGRESS) {
            (holder as ProgressViewHolder).bind()

            //TODO: Load more full span (StaggeredGridLayoutManager).
            val layoutParams: ViewGroup.LayoutParams = holder.itemView.layoutParams
            if (layoutParams != null && layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                layoutParams.isFullSpan = true
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (mList != null) {
            if (mList[position] is LoadMoreObject) {
                return Constants.VIEW_PROGRESS
            } else {
                return Constants.VIEW_ITEM
            }
        }
        return super.getItemViewType(position)
    }

    class ProgressViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_recycler_circle_progress)) {

        fun bind() {
            itemView.progress_circle.visibility = View.VISIBLE
        }
    }

    protected abstract fun setLayout(inflater: LayoutInflater, parent: ViewGroup?): View

    protected abstract fun createViewHolder(view: View): VH

    protected abstract fun displayItem(viewHolder: VH, position: Int)

    /**
     * Add item
     *
     * @param position getItemCount or mList.size()
     * @param item     new object
     * @param isScroll
     */
    override fun insertItem(position: Int, item: T, isScroll: Boolean) {
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
    override fun insertItem(positionStart: Int, itemList: List<T>, isScroll: Boolean) {
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