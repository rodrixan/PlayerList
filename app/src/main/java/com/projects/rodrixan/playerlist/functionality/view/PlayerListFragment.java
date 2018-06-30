package com.projects.rodrixan.playerlist.functionality.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.projects.rodrixan.playerlist.R;
import com.projects.rodrixan.playerlist.common.base.BaseFragment;
import com.projects.rodrixan.playerlist.common.view.recyclerview.DividerColorItemDecoration;
import com.projects.rodrixan.playerlist.common.view.recyclerview.ReferencedSwipeRefreshLayout;
import com.projects.rodrixan.playerlist.functionality.presenter.PlayerListPresenter;
import com.projects.rodrixan.playerlist.functionality.presenter.PlayerListPresenterImpl;
import com.projects.rodrixan.playerlist.functionality.service.PlayerListManager;
import com.projects.rodrixan.playerlist.functionality.service.PlayerListManagerImpl;
import com.projects.rodrixan.playerlist.model.Player;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.IFlexible;

@EFragment(R.layout.fragment_player_list)
public class PlayerListFragment extends BaseFragment implements PlayerListView,
        SwipeRefreshLayout.OnRefreshListener {

    @ViewById(R.id.player_list_swipe_layout)
    protected ReferencedSwipeRefreshLayout mSwipeRefreshLayout;
    @ViewById(R.id.player_list_empty_view)
    protected ConstraintLayout mEmptyViewLayout;
    @ViewById(R.id.error_view_textview)
    protected TextView mErrorTextView;
    @ViewById(R.id.players_recycler_view)
    protected RecyclerView mRecyclerView;

    @Bean(PlayerListPresenterImpl.class)
    protected PlayerListPresenter mPresenter;

    private FlexibleAdapter<IFlexible> mAdapter;

    @AfterInject
    protected void setupPresenter() {
        if (mPresenter != null) {
            mPresenter.initPresenter(this);
        }
    }

    @Override
    public final void onResume() {
        super.onResume();
        getPlayers();
    }

    private void getPlayers() {
        showLoader();
        if (mPresenter != null) {
            mPresenter.getPlayerList();
        }

    }

    @Override
    public void onPlayersReceived(List<IFlexible> players) {
        hideLoader();
        loadData(players);
        showError(players == null || players.isEmpty(),
                getString(R.string.player_list_empty_results));
        setRefreshing(mSwipeRefreshLayout, false);
    }

    private void loadData(List<IFlexible> players) {
        loadAdapter(players);
        setUpRecyclerView();
        setUpSwipeLayout();
    }

    private void loadAdapter(List<IFlexible> userList) {
        if (mAdapter == null) {
            mAdapter = new FlexibleAdapter<>(userList);
            mAdapter.setDisplayHeadersAtStartUp(true).setStickyHeaders(true);
        }
        else {
            mAdapter.updateDataSet(userList, false);
        }

    }

    private void setUpRecyclerView() {
        if (mRecyclerView != null) {
            if (mRecyclerView.getLayoutManager() == null) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1,
                        GridLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(gridLayoutManager);

            }
            if (mRecyclerView.getAdapter() == null) {
                mRecyclerView.setAdapter(mAdapter);
            }
            else {
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
            if (mRecyclerView.getItemDecorationCount() == 0) {
                mRecyclerView.addItemDecoration(new DividerColorItemDecoration(getContext(),
                        R.drawable.recyclerview_line_divider_grey));
            }
        }
    }

    private void setUpSwipeLayout() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }
    }

    public void setRefreshing(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    @Override
    public void onServiceError(String message) {
        if (message != null && !message.isEmpty()) {
            showError(true, message);
        }
        setRefreshing(mSwipeRefreshLayout, false);
    }

    private void showError(boolean show, String msg) {
        if (mEmptyViewLayout != null && mRecyclerView != null) {
            if (show) {
                mEmptyViewLayout.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                if (mErrorTextView != null) {
                    mErrorTextView.setText(msg);
                }
            }
            else {
                mEmptyViewLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        }
        hideLoader();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void onRefresh() {
        getPlayers();
    }
}
