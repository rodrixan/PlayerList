package com.projects.rodrixan.playerlist.functionality.presenter;

import com.projects.rodrixan.playerlist.functionality.service.PlayerListManager;
import com.projects.rodrixan.playerlist.functionality.service.PlayerListManagerImpl;
import com.projects.rodrixan.playerlist.functionality.view.PlayerListView;
import com.projects.rodrixan.playerlist.model.Player;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

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
    public void onUsersReceivedFromService(List<Player> players) {
        if(mView!=null){
            mView.onPlayersReceived(players);
        }
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
