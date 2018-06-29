package com.projects.rodrixan.playerlist.activity;

import android.os.Bundle;

import com.projects.rodrixan.playerlist.R;
import com.projects.rodrixan.playerlist.common.base.BaseActivity;
import com.projects.rodrixan.playerlist.common.base.BaseFragment;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_player_list)
public class PlayerListActivity extends BaseActivity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            showContents();
        }
    }

    private void showContents() {
//        final BaseFragment fragment = RandomUsersListFragment_.builder()
//                .build();
        BaseFragment fragment= null;

        if (getSupportFragmentManager() != null) {
            transactionSubmodule(getSupportFragmentManager(), R.id.main_container, fragment,
                    fragment.getFragmentId(), fragment.getFragmentId(), false, false);
        }
    }
}
