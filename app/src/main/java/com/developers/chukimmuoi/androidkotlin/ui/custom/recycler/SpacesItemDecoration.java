package com.developers.chukimmuoi.androidkotlin.ui.custom.recycler;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author : Hanet Electronics
 * @Skype : chukimmuoi
 * @Mobile : +84 167 367 2505
 * @Email : muoick@hanet.com
 * @Website : http://hanet.com/
 * @Project : AndroidKotlin
 * Created by chukimmuoi on 6/25/17.
 *
 *
 * Decorator which adds spacing around the tiles in a Grid layout RecyclerView. Apply to a RecyclerView with:
 * SpacesItemDecoration decoration = new SpacesItemDecoration(16);
 * mRecyclerView.addItemDecoration(decoration);
 * <p>
 * Feel free to add any value you wish for SpacesItemDecoration. That value determines the amount of spacing.
 * Source: http://blog.grafixartist.com/pinterest-masonry-layout-staggered-grid/
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private int mSpanCount;
    private int mLinearLayoutType;

    public SpacesItemDecoration(SpacesItemDecorationBuilder builder) {
        this.mSpace            = builder.space;
        this.mSpanCount        = builder.spanCount;
        this.mLinearLayoutType = builder.linearLayoutType;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mSpace > 0) {
            if (mSpanCount > 0) {
                outRect.left   = mSpace;
                outRect.right  = mSpace;
                outRect.bottom = mSpace;
                outRect.top    = mSpace;
                //int position = parent.getChildAdapterPosition(view);
            } else {
                if (mLinearLayoutType == LinearLayoutManager.HORIZONTAL) {
                    outRect.right = mSpace;
                } else if (mLinearLayoutType == LinearLayoutManager.VERTICAL) {
                    outRect.bottom = mSpace;
                }
            }
        }
    }

    public static class SpacesItemDecorationBuilder {
        private int space            = 0;
        private int spanCount        = -1;
        private int linearLayoutType = -1;

        public SpacesItemDecorationBuilder(int space) {
            this.space = space;
        }

        public SpacesItemDecorationBuilder setSpanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }

        public SpacesItemDecorationBuilder setLinearLayoutType(int linearLayoutType) {
            this.linearLayoutType = linearLayoutType;
            return this;
        }

        public SpacesItemDecoration build() {
            return new SpacesItemDecoration(this);
        }
    }
}
