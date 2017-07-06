package com.developers.chukimmuoi.androidkotlin.commons.recycler

import android.content.Context
import android.support.v7.widget.*
import android.util.AttributeSet
import android.view.Gravity
import com.developers.chukimmuoi.androidkotlin.commons.recycler.gravitysnaphelper.GravityPagerSnapHelper
import com.developers.chukimmuoi.androidkotlin.commons.recycler.gravitysnaphelper.GravitySnapHelper
import com.developers.chukimmuoi.androidkotlin.commons.recycler.listener.EndlessRecyclerViewScrollListener
import com.developers.chukimmuoi.androidkotlin.constants.Constants

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/23/17.
 */

open class BaseRecyclerView : RecyclerView, IBaseRecyclerView {

    private val TAG = BaseRecyclerView::class.java.simpleName

    private var mContext: Context? = null

    private var mTypeLayout: Int = Constants.LINEAR_LAYOUT

    private var mLinearLayoutManager: LinearLayoutManager? = null

    private var mGridLayoutManager: GridLayoutManager? = null

    private var mStaggeredGridLayoutManager: StaggeredGridLayoutManager? = null

    private var mEndlessScrollListener: EndlessRecyclerViewScrollListener? = null

    private var mOnEndlessScrolling: OnEndlessScrolling? = null

    private var mItemDecoration: ItemDecoration? = null

    constructor(context: Context?) : super(context) {
        mContext = context
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle){
        mContext = context
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
    }

    /**
     * Set layout manager
     *
     * @param typeLayout   [Constants.LINEAR_LAYOUT], [Constants.GRID_LAYOUT], [Constants.STAGGERED_GRID_LAYOUT]
     * @param spanCount    use when typeLayout = [Constants.GRID_LAYOUT] or [Constants.STAGGERED_GRID_LAYOUT]
     * @param isHorizontal is "true"  ---> Chieu ngang.
     *                     is "false" ---> Chieu doc(*).
     * @param isReverse    is "true"  ---> Dao huong scroll.(right -> left & top -> bottom).
     *                     is "false" ---> normal(*).
     */
    override fun initLayoutManager(typeLayout: Int, spanCount: Int, isHorizontal: Boolean, isReverse: Boolean) {
        mTypeLayout = typeLayout
        when (mTypeLayout) {
            Constants.LINEAR_LAYOUT -> {
                mLinearLayoutManager = LinearLayoutManager(mContext, if (isHorizontal) LinearLayoutManager.HORIZONTAL
                else LinearLayoutManager.VERTICAL, isReverse)

                mItemDecoration = SpacesItemDecoration.SpacesItemDecorationBuilder(Constants.SPACES_ITEM).setLinearLayoutType(if (isHorizontal) DividerItemDecoration.HORIZONTAL
                else DividerItemDecoration.VERTICAL).build()

                layoutManager = mLinearLayoutManager
            }
            Constants.GRID_LAYOUT -> {
                if (spanCount > 0) {
                    mGridLayoutManager = GridLayoutManager(mContext, spanCount, if (isHorizontal) GridLayoutManager.HORIZONTAL
                    else GridLayoutManager.VERTICAL, isReverse)

                    //TODO: Load more full span (GridLayoutManager).
                    mGridLayoutManager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            val adapter = adapter as BaseRecyclerAdapter
                            when (adapter.getItemViewType(position)) {
                                Constants.VIEW_ITEM -> return 1
                                Constants.VIEW_PROGRESS -> return spanCount
                                else -> return 0
                            }
                        }
                    }

                    mItemDecoration = SpacesItemDecoration.SpacesItemDecorationBuilder(Constants.SPACES_ITEM).setSpanCount(spanCount).build()

                    layoutManager = mGridLayoutManager
                }

            }
            Constants.STAGGERED_GRID_LAYOUT -> {
                if (spanCount > 0) {
                    mStaggeredGridLayoutManager = StaggeredGridLayoutManager(spanCount, if (isHorizontal) StaggeredGridLayoutManager.HORIZONTAL
                    else StaggeredGridLayoutManager.VERTICAL)

                    mItemDecoration = SpacesItemDecoration.SpacesItemDecorationBuilder(Constants.SPACES_ITEM).setSpanCount(spanCount).build()

                    layoutManager = mStaggeredGridLayoutManager
                }
            }
        }

        addItemDecoration(mItemDecoration)
    }

    override fun initLinearLayoutManager() {
        initLayoutManager(Constants.LINEAR_LAYOUT)
    }

    override fun initGridLayoutManager(spanCount: Int) {
        initLayoutManager(Constants.GRID_LAYOUT, spanCount)
    }

    override fun initStaggeredGridLayoutManager(spanCount: Int) {
        initLayoutManager(Constants.STAGGERED_GRID_LAYOUT, spanCount)
    }

    override fun addOnScrollListener(listener: OnScrollListener?) {
        super.addOnScrollListener(listener)
    }

    interface OnEndlessScrolling {
        /**
         * Load new data

         * @see {http://stackoverflow.com/questions/39445330/cannot-call-notifyiteminserted-method-in-a-scroll-callback-recyclerview-v724-2}
         *
         *
         * view.post{ mAdapter.insertItem(positionStart, insertList) }
         * [BaseRecyclerAdapter.insertItem]
         */
        fun loadNextPage(page: Int, totalItemsCount: Int, view: RecyclerView)

        /**
         * Clear all data ---> new action.
         *
         *
         * mList.clear();
         * mAdapter.notifyDataSetChanged(); [BaseRecyclerAdapter.reloadAll]
         * mEndlessScrollListener.resetState(); [resetStateEndless]
         */
        fun resetEndless()
    }

    fun resetStateEndless(): Boolean {
        if (mEndlessScrollListener != null) {
            mEndlessScrollListener?.resetState()
            return true
        }
        return false
    }

    fun setOnEndlessScrolling(onEndlessScrolling: OnEndlessScrolling) {
        mOnEndlessScrolling = onEndlessScrolling

        when (mTypeLayout) {
            Constants.LINEAR_LAYOUT -> mEndlessScrollListener = object : EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    actionLoadMore(page, totalItemsCount, view)
                }
            }
            Constants.GRID_LAYOUT -> mEndlessScrollListener = object : EndlessRecyclerViewScrollListener(mGridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    actionLoadMore(page, totalItemsCount, view)
                }
            }
            Constants.STAGGERED_GRID_LAYOUT -> mEndlessScrollListener = object : EndlessRecyclerViewScrollListener(mStaggeredGridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    actionLoadMore(page, totalItemsCount, view)
                }
            }
        }

        if (mEndlessScrollListener != null) {
            addOnScrollListener(mEndlessScrollListener);
        }
    }

    private fun actionLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
        mOnEndlessScrolling?.loadNextPage(page, totalItemsCount, view)
    }

    /**
     * Use Linear Layout NOT Grid layout
     *
     * @see {https://github.com/rubensousa/RecyclerViewSnap}
     *
     * @param typeGravity [Gravity.CENTER] use linear layout VERTICAL & HORIZONTAL
                          [Gravity.START], [Gravity.END] use linear layout HORIZONTAL
                          [Gravity.TOP], [Gravity.BOTTOM] use linear layout VERTICAL
     * @param isSnapPager use linear layout HORIZONTAL
     */
    override fun setLinearSnapHelper(typeGravity: Int, isSnapPager: Boolean) {
        if (mLinearLayoutManager != null) {
            if (typeGravity === Gravity.CENTER) {
                //Snap center: HORIZONTAL && VERTICAL.
                val snapHelper = LinearSnapHelper()
                onFlingListener = null
                snapHelper.attachToRecyclerView(this)
                return
            }

            if (isSnapPager) {
                //Snap pager: HORIZONTAL ===> Gravity.START || Gravity.END.
                val gravityPagerSnapHelper = GravityPagerSnapHelper(typeGravity)
                onFlingListener = null
                gravityPagerSnapHelper.attachToRecyclerView(this)
            } else {
                //Snap normal: HORIZONTAL ===> Gravity.START || Gravity.END,
                //             VERTICAL   ===> Gravity.TOP   || Gravity.BOTTOM.
                val gravitySnapHelper = GravitySnapHelper(typeGravity)
                onFlingListener = null
                gravitySnapHelper.attachToRecyclerView(this)
            }
        }
    }
}