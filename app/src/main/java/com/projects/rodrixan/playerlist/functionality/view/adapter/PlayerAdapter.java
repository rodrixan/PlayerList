package com.projects.rodrixan.playerlist.functionality.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.projects.rodrixan.playerlist.common.view.recyclerview.RecyclerViewAdapterBase;
import com.projects.rodrixan.playerlist.common.view.recyclerview.ViewWrapper;
import com.projects.rodrixan.playerlist.model.Player;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class PlayerAdapter extends RecyclerViewAdapterBase<Player, PlayerViewHolder> {

    @RootContext
    protected Context mContext;

    @Override
    protected PlayerViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        PlayerViewHolder view = PlayerViewHolder_.build(mContext);
        view.setMinimumWidth(parent.getMeasuredWidth());

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<PlayerViewHolder> holder, int position) {
        PlayerViewHolder view = holder.getView();
        Player current = mItems.get(position);
        view.bind(current);
    }

}
