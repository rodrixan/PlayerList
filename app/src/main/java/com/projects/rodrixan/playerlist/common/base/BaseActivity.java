package com.projects.rodrixan.playerlist.common.base;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.projects.rodrixan.playerlist.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @ViewById(R.id.loading_view)
    protected ConstraintLayout mLoaderView;


    public void showLoader() {
        if (mLoaderView != null) {
            mLoaderView.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoader() {
        if (mLoaderView != null) {
            mLoaderView.setVisibility(View.GONE);
        }
    }

    public void transactionSubmodule(FragmentManager fm, int container, BaseFragment frag, String
            fragName, String transacName, boolean anim, boolean stack) {
        Log.d(TAG, "transactionSubmodule " + fragName);
        if (!frag.isAdded()) {
            final FragmentTransaction trans = fm.beginTransaction();

            if (anim) {
                trans.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left, R.anim.slide_out_to_right);

            }
            trans.replace(container, frag, fragName);

            if (stack) {
                trans.addToBackStack(transacName);
            }

            trans.commitAllowingStateLoss();
        }
    }
}
