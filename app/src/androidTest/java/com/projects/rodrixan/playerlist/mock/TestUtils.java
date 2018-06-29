package com.projects.rodrixan.playerlist.mock;

import android.app.Activity;
import android.content.Context;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.projects.rodrixan.playerlist.action.ClickOnRecyclerViewItemView;

import org.hamcrest.Matcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isNotNull;

public final class TestUtils {

    private static Activity sCurrentActivity;

    private TestUtils() {
    }

    public static String getStringFromFile(final Context context, final String filePath) throws
            Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        final String ret = convertStreamToString(stream);
        //Close all streams.
        stream.close();
        return ret;
    }

    public static String convertStreamToString(final InputStream is) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static RecyclerView getRecyclerView(final int viewId) {
        try {
            getCurrentActivity();

            final RecyclerView recyclerView = sCurrentActivity.findViewById(viewId);
            assertNotNull(recyclerView);

            return recyclerView;

        } catch (final Throwable throwable) {
            return null;
        }
    }

    public static Activity getCurrentActivity() throws Throwable {
        getInstrumentation().runOnMainSync(() -> {
            final Collection<Activity> resumedActivity = ActivityLifecycleMonitorRegistry
                    .getInstance()
                    .getActivitiesInStage(Stage.RESUMED);
            for (final Activity act : resumedActivity) {
                sCurrentActivity = act;
                break;
            }

        });
        return sCurrentActivity;
    }

    public static void checkNumberOfEntries(final RecyclerView listView, final int expected) {
        final RecyclerView.Adapter listAdapter = listView.getAdapter();

        assertNotNull(listAdapter);
        assertEquals(expected, listAdapter.getItemCount());
    }

    public static void waitOnViewAndCheckNotNull(Matcher<View> matcher, int
            timeOut) {
        boolean found = false;
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + timeOut;
        do {
            try {
                onView(matcher).check(isNotNull());

                found = true;
            } catch (Throwable e) {

            }
        } while (!found && System.currentTimeMillis() < endTime);
    }


    public static void waitOnViewAndPerform(int timeOut, Matcher<View> matcher, ViewAction...
            viewActions) {
        boolean found = false;
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + timeOut;

        do {
            try {
                onView(matcher).perform(viewActions);

                found = true;
            } catch (Throwable e) {

            }
        } while (!found && System.currentTimeMillis() < endTime);
    }

    public static void performClickRecyclerViewItem(int listId, int position) {
        onView(allOf(isDisplayed(), withId(listId)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, scrollTo()));

        onView(allOf(isDisplayed(), withId(listId)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }


    public static void clickOnRecyclerViewChildView(int timeout,int recyclerViewId, int position, int viewId){
        waitOnViewAndPerform(timeout, withId(recyclerViewId),
                RecyclerViewActions.actionOnItemAtPosition(position,
                        ClickOnRecyclerViewItemView.clickChildViewWithId(
                                viewId)));

    }

    public static void checkViewHasTheCorrectDrawable(int viewId, int drawableToCheckId){
        onView(allOf(withId(viewId),
                withTagValue(equalTo(drawableToCheckId)))).check(
                matches(isDisplayed()));
    }
}
