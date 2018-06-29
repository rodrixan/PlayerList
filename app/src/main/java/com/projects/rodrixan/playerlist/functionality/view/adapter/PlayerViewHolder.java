package com.projects.rodrixan.playerlist.functionality.view.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.rodrixan.playerlist.R;
import com.projects.rodrixan.playerlist.common.util.MetricsUtils;
import com.projects.rodrixan.playerlist.common.util.StringUtils;
import com.projects.rodrixan.playerlist.model.Player;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EViewGroup(R.layout.item_list_player)
public class PlayerViewHolder extends ConstraintLayout {

    private static final int THUMBNAIL_PIC_SIZE_DP = 100;

    @ViewById(R.id.item_list_player_image)
    protected ImageView mPlayerImageIV;
    @ViewById(R.id.item_list_user_name)
    protected TextView mUserNameTV;
    @ViewById(R.id.item_list_player_date)
    protected TextView mPlayerDateTV;
    @ViewById(R.id.item_list_player_sport)
    protected TextView mPlayerSport;

    private Context mContext;


    public PlayerViewHolder(Context context) {
        super(context);
        mContext = context;
    }

    public void bind(final Player player) {
        if (player != null) {
            setName(player);
            setDate(player);
            setSport(player);
            setUserImage(player.getImage());

        }
    }

    private void setSport(Player user) {
        String sport = user.getSport();
        if (mPlayerSport != null && sport != null) {
            mPlayerSport.setText(sport);
        }
    }

    private void setDate(Player user) {
        Date date = user.getDate();
        if (mPlayerDateTV != null && date != null) {
            mPlayerDateTV.setText(StringUtils.parseDDMMYYYDate(date));
        }
    }

    private void setName(Player user) {
        String name = user.getName()+" "+user.getSurname();
        if (mUserNameTV != null) {
            mUserNameTV.setText(name);
        }
    }

    private void setUserImage(String imageUrl) {
        if (imageUrl != null && mPlayerImageIV != null) {
            int sizeInPixels = MetricsUtils.convertDipToPixels(mContext, THUMBNAIL_PIC_SIZE_DP);
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .resize(150, 150)
                    .error(R.drawable.ic_error_badge)
                    .into(mPlayerImageIV);

        }
    }


}
