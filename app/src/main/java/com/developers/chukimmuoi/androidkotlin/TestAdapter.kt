package com.developers.chukimmuoi.androidkotlin

import android.app.Activity
import com.developers.chukimmuoi.androidkotlin.commons.adapter.ItemAdapter
import com.developers.chukimmuoi.androidkotlin.commons.recycler.BaseRecyclerAdapter
import com.developers.chukimmuoi.androidkotlin.commons.recycler.BaseRecyclerView
import com.developers.chukimmuoi.androidkotlin.listener.onclick.OnItemClickListener

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/29/17.
 */

class TestAdapter(context: Activity,
                  recyclerView: BaseRecyclerView,
                  list: MutableList<ItemAdapter>,
                  listener: OnItemClickListener? = null)
    : BaseRecyclerAdapter(context, recyclerView, list) {

    private val TAG = TestAdapter::class.java.simpleName

    private var mListener: OnItemClickListener? = listener

    init {
        mDelegateManager.addDelegate(ContactDelegate(mContext, mListener))
    }
}