package com.developers.chukimmuoi.androidkotlin.commons.recycler.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developers.chukimmuoi.androidkotlin.R;

/**
 * @author  : Hanet Electronics
 * @Skype   : chukimmuoi
 * @Mobile  : +84 167 367 2505
 * @Email   : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/25/17.
 *
 * @see {https://gist.github.com/nesquena/231e356f372f214c4fe6}
 * USAGE:
 *
 *  ItemRecyclerClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemRecyclerClickSupport.OnItemClickListener() {
 *      @Override
 *      public void onItemClicked(RecyclerView recyclerView, int position, View v) {
 *          // do it
 *      }
 *  });
 *
 *  ItemRecyclerClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemRecyclerClickSupport.OnItemLongClickListener() {
 *      @Override
 *      public void onItemLongClicked(RecyclerView recyclerView, int position, View v) {
 *          // do it
 *          return true; ===> // OnItemClickListener
 *          or
 *          return false;===> # OnItemClickListener
 *      }
 *  });
 */

public class ItemRecyclerClickSupport {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private ItemRecyclerClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    public static ItemRecyclerClickSupport addTo(RecyclerView view) {
        ItemRecyclerClickSupport support = (ItemRecyclerClickSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemRecyclerClickSupport(view);
        }
        return support;
    }

    public static ItemRecyclerClickSupport removeFrom(RecyclerView view) {
        ItemRecyclerClickSupport support = (ItemRecyclerClickSupport) view.getTag(R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public ItemRecyclerClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public ItemRecyclerClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener {

        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {

        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
