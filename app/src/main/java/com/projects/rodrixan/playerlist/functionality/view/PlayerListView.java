package com.projects.rodrixan.playerlist.functionality.view;

import com.projects.rodrixan.playerlist.common.view.BaseView;
import com.projects.rodrixan.playerlist.model.Player;

import java.util.List;

import eu.davidea.flexibleadapter.items.IFlexible;

public interface PlayerListView extends BaseView {

    void onPlayersReceived(List<IFlexible> players);
}
