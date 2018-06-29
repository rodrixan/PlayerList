

package com.projects.rodrixan.playerlist.common.view.recyclerview;

/**
 * Created by david.vallejo.prieto on 30/09/16.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.projects.rodrixan.playerlist.R;

public class ReferencedSwipeRefreshLayout extends SwipeRefreshLayout {
    RecyclerView mRecyclerView;
    int mAdapterViewId;

    public ReferencedSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray mStyledAttributes = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ReferencedSwipeRefreshLayout, 0, 0);
        mAdapterViewId = mStyledAttributes.getResourceId(
                R.styleable.ReferencedSwipeRefreshLayout_adapter_view, -1);
        mStyledAttributes.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View adapterView = findViewById(mAdapterViewId);
        if (adapterView instanceof RecyclerView) {
            mRecyclerView = (RecyclerView) adapterView;
        }
    }

    @Override
    public boolean canChildScrollUp() {
        if (mRecyclerView != null) {
            return mRecyclerView.canScrollVertically(-1);
        }
        else {
            return false;
        }
    }
}