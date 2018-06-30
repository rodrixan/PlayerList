package com.projects.rodrixan.playerlist.functionality.presenter;

import android.support.annotation.NonNull;

import com.projects.rodrixan.playerlist.functionality.service.PlayerListManager;
import com.projects.rodrixan.playerlist.functionality.service.PlayerListManagerImpl;
import com.projects.rodrixan.playerlist.functionality.view.PlayerListView;
import com.projects.rodrixan.playerlist.functionality.view.adapter.ListHeaderViewHolder;
import com.projects.rodrixan.playerlist.functionality.view.adapter.ListPlayerViewHolder;
import com.projects.rodrixan.playerlist.model.Player;
import com.projects.rodrixan.playerlist.model.PlayerResponse;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.items.IFlexible;

@EBean(scope = EBean.Scope.Singleton)
public class PlayerListPresenterImpl implements PlayerListPresenter, PlayerListManager
        .PlayerListCallback {

    @Bean(PlayerListManagerImpl.class)
    protected PlayerListManager mListService;

    private PlayerListView mView;

    @Override
    public void initPresenter(PlayerListView view) {
        mView = view;
    }

    @Override
    public void getPlayerList() {
        if (mListService != null) {
            mListService.getPlayers(this);
        }
    }

    @Override
    public void onUsersReceivedFromService(List<PlayerResponse> players) {
        parsePlayersToViewData(players);
    }

    @Background
    protected void parsePlayersToViewData(List<PlayerResponse> playerResponseList) {
        List<IFlexible> parsed = new ArrayList<>();
        for (PlayerResponse playerResponse : playerResponseList) {
            String headerId = playerResponseList.indexOf(playerResponse) + "";
            ListHeaderViewHolder headerViewHolder = createHeader(playerResponse, headerId);
            addPlayersWithHeader(parsed, playerResponse, headerViewHolder);
        }
        returnDataToView(parsed);
    }

    @UiThread
    protected void returnDataToView(List<IFlexible> data) {
        if (mView != null) {
            mView.onPlayersReceived(data);
        }
    }

    private void addPlayersWithHeader(List<IFlexible> parsed, PlayerResponse playerResponse,
            ListHeaderViewHolder headerViewHolder) {
        List<Player> players = playerResponse.getPlayers();
        for (Player player : players) {
            String playerId = players.indexOf(player) + "";
            parsed.add(new ListPlayerViewHolder(headerViewHolder, playerId,
                    player.getName() + " " + player.getSurname(), player.getDate(),
                    player.getImage()));
        }
    }

    @NonNull
    private ListHeaderViewHolder createHeader(PlayerResponse playerResponse, String headerId) {
        String title = playerResponse.getTitle();
        return new ListHeaderViewHolder(headerId, title);
    }

    @Override
    public void onPlayerError(String message) {
        if (mView != null) {
            mView.onServiceError(message);
        }
    }

    @Override
    public void cancelUsersCall() {
        if (mListService != null) {
            mListService.cancelPlayersCall();
        }
    }

}
