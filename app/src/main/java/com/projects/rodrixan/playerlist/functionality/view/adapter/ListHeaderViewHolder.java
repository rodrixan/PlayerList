package com.projects.rodrixan.playerlist.functionality.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.projects.rodrixan.playerlist.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class ListHeaderViewHolder extends AbstractHeaderItem<ListHeaderViewHolder
        .HeaderViewHolder> {

    private String mTitle;
    private String mId;

    public ListHeaderViewHolder(String id, String tittle) {
        super();
        mId = id;
        mTitle = tittle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof ListHeaderViewHolder) {
            ListHeaderViewHolder inItem = (ListHeaderViewHolder) inObject;
            return this.mId.equals(inItem.mId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return mId.hashCode();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_list_header;
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, HeaderViewHolder holder, int
            position, List<Object> payloads) {
        if (holder != null && holder.mHeaderTittle != null) {
            holder.mHeaderTittle.setText(getTitle());
        }
    }

    @Override
    public HeaderViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new HeaderViewHolder(view, adapter);
    }

    static class HeaderViewHolder extends FlexibleViewHolder {

        private TextView mHeaderTittle;

        public HeaderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter, true);
            mHeaderTittle = view.findViewById(R.id.item_list_header_text);
            setFullSpan(true);
        }

    }
}
