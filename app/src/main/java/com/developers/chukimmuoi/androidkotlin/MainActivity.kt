package com.developers.chukimmuoi.androidkotlin

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.developers.chukimmuoi.androidkotlin.commons.adapter.ItemAdapter
import com.developers.chukimmuoi.androidkotlin.commons.recycler.BaseRecyclerView
import com.developers.chukimmuoi.androidkotlin.commons.recycler.model.LoadMoreCircleItem
import com.developers.chukimmuoi.androidkotlin.constants.Constants
import com.developers.chukimmuoi.androidkotlin.data.model.Contact
import com.developers.chukimmuoi.androidkotlin.listener.onclick.OnItemClickListener
import com.developers.chukimmuoi.androidkotlin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : BaseActivity(), OnItemClickListener, BaseRecyclerView.OnEndlessScrolling {

    private val TAG = MainActivity::class.java.simpleName

    private var mTestAdapter: TestAdapter? = null

    private var mList: MutableList<ItemAdapter> = Contact.createContactsList(20, 0)

    private val mHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTestAdapter = TestAdapter(this, recyclerView = rv_contact, list = mList, listener = this)
        rv_contact.adapter = mTestAdapter
        rv_contact.initLayoutManager(Constants.LINEAR_LAYOUT, 0, false, false)
        rv_contact.setHasFixedSize(true)
        rv_contact.itemAnimator = DefaultItemAnimator()
        rv_contact.setLinearSnapHelper(Gravity.BOTTOM)
        rv_contact.setOnEndlessScrolling(this)

//        rv_contact.addOnItemTouchListener(ItemRecyclerClickListener(this@MainActivity, rv_contact, object : ItemRecyclerClickListener.OnItemClickListener {
//            override fun onItemClick(view: View, position: Int) {
//                showToast("Item click position = " + position)
//            }
//
//            override fun onLongItemClick(view: View, position: Int) {
//                showToast("Long item click position = " + position)
//            }
//        }))

//        ItemRecyclerClickSupport.addTo(rv_contact)
//                .setOnItemClickListener { _, position, _ ->
//                    showToast("Item click position = " + position) }
//
//        ItemRecyclerClickSupport.addTo(rv_contact)
//                .setOnItemLongClickListener { _, position, _ ->
//                    showToast("Long item click position = " + position)
//                    true
//        }

        swipeContainer.setOnRefreshListener { mHandler.postDelayed({ swipeContainer.isRefreshing = false }, 3000) }
    }

    override fun onItemClick(className: String, itemView: View, position: Int) {
        showToast("Test $position", true)
    }

    /**
     * Load new data

     * @see {http://stackoverflow.com/questions/39445330/cannot-call-notifyiteminserted-method-in-a-scroll-callback-recyclerview-v724-2}
     *
     *
     * view.post{ mAdapter.insertItem(positionStart, insertList) }
     * [BaseRecyclerAdapter.insertItem]
     */
    override fun loadNextPage(page: Int, totalItemsCount: Int, view: RecyclerView) {
        val size = mList.size
        if (mList[size - 1] is LoadMoreCircleItem) {
            return
        }
        mTestAdapter?.insertItem(size, LoadMoreCircleItem())

        mHandler.postDelayed({
            val size1 = mList.size
            mTestAdapter?.removeItem(size1 - 1)

            val moreContacts = Contact.createContactsList(10, page)
            val curSize = mTestAdapter?.itemCount ?: 0
            mTestAdapter?.insertItem(curSize, moreContacts)
        }, (1 * 1000).toLong())
    }

    /**
     * Clear all data ---> new action.

     * mList.clear();
     * mAdapter.notifyDataSetChanged(); [BaseRecyclerAdapter.reloadAll]
     * mEndlessScrollListener.resetState(); [resetStateEndless]
     */
    override fun resetEndless() {
        mList.clear()
        mTestAdapter?.reloadAll()
        rv_contact.resetStateEndless()
    }
}
