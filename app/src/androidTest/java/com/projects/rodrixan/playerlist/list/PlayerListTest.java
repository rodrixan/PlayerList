package com.projects.rodrixan.playerlist.list;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.AutoCompleteTextView;

import com.projects.rodrixan.playerlist.R;
import com.projects.rodrixan.playerlist.activity.PlayerListActivity_;
import com.projects.rodrixan.playerlist.mock.MockedServer;
import com.projects.rodrixan.playerlist.mock.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class PlayerListTest {
    private static final int VIEW_CHECK_TIMEOUT = 2000;

    @Rule
    public ActivityTestRule<PlayerListActivity_> mActivityTestRule = new ActivityTestRule<>(
            PlayerListActivity_.class, true, false);

    private MockedServer mServer;

    @Before
    public void setUp() throws Exception {
        mServer = new MockedServer();
        mActivityTestRule.launchActivity(new Intent());
    }

    @After
    public void tearDown() throws Exception {
        mServer.shutdownServer();
    }

    @Test
    public void whenCallServiceReceivePlayers() {
        //30 users + 4 headers
        final int expectedPlayersNumber=34;
        final RecyclerView detailRecyclerView = TestUtils.getRecyclerView(R.id.players_recycler_view);
        TestUtils.waitOnViewAndCheckNotNull(withId(R.id.players_recycler_view), VIEW_CHECK_TIMEOUT);
        TestUtils.checkNumberOfEntries(detailRecyclerView, expectedPlayersNumber);
    }

}
