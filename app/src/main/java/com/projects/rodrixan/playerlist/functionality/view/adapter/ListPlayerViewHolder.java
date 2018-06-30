package com.projects.rodrixan.playerlist.functionality.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.rodrixan.playerlist.R;
import com.projects.rodrixan.playerlist.common.util.MetricsUtils;
import com.projects.rodrixan.playerlist.common.util.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

public class ListPlayerViewHolder extends AbstractSectionableItem<ListPlayerViewHolder
        .ItemViewHolder, ListHeaderViewHolder> {

    private static final int THUMBNAIL_PIC_SIZE_DP = 50;

    private String mId;
    private String mSport;
    private String mName;
    private Date mDate;
    private String mImageUrl;

    public ListPlayerViewHolder(ListHeaderViewHolder header, String id, String name, Date date,
            String imageUrl) {
        super(header);
        setSelectable(true);
        if (header != null) {
            mSport = header.getTitle();
        }
        mId = id;
        mName = name;
        mDate = date;
        mImageUrl = imageUrl;

    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ItemViewHolder holder, int
            position, List<Object> payloads) {

        if (holder != null) {
            Context context = holder.itemView.getContext();

            setPlayerImage(context, mImageUrl, holder);
            setHolderSport(holder);
            setHolderName(holder);
            setHolderDate(holder);

        }
    }

    private void setHolderSport(ItemViewHolder holder) {
        if (mSport != null && holder.mPlayerSport != null) {
            holder.mPlayerSport.setText(mSport);
        }
    }

    private void setHolderName(ItemViewHolder holder) {
        if (mName != null && holder.mPlayerName != null) {
            holder.mPlayerName.setText(mName);
        }
    }

    private void setHolderDate(ItemViewHolder holder) {
        if (holder.mPlayerDate != null) {
            if (mDate != null) {
                holder.mPlayerDate.setVisibility(View.VISIBLE);
                holder.mPlayerDate.setText(StringUtils.parseDDMMYYYDate(mDate));
            }else{
                holder.mPlayerDate.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof ListPlayerViewHolder) {
            ListPlayerViewHolder inItem = (ListPlayerViewHolder) inObject;
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
        return R.layout.item_list_player;
    }

    @Override
    public ItemViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new ItemViewHolder(view, adapter);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    private void setPlayerImage(Context context, String imageUrl, ItemViewHolder holder) {
        if (context != null && imageUrl != null && holder != null && holder.mPlayerImage != null) {
            int sizeInPixels = MetricsUtils.convertDipToPixels(context, THUMBNAIL_PIC_SIZE_DP);
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(ContextCompat.getDrawable(context,R.drawable.ic_image_placeholder))
                    .resize(sizeInPixels, sizeInPixels)
                    .error(ContextCompat.getDrawable(context,R.drawable.ic_error_badge))
                    .into(holder.mPlayerImage);

        }
    }

    protected class ItemViewHolder extends FlexibleViewHolder {

        protected ImageView mPlayerImage;
        protected TextView mPlayerName;
        protected TextView mPlayerDate;
        protected TextView mPlayerSport;

        public ItemViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            mPlayerImage = view.findViewById(R.id.item_list_player_image);
            mPlayerName = view.findViewById(R.id.item_list_player_name);
            mPlayerDate = view.findViewById(R.id.item_list_player_date);
            mPlayerSport = view.findViewById(R.id.item_list_player_sport);
        }
    }
}
