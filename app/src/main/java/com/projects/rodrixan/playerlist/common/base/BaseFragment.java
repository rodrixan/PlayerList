package com.projects.rodrixan.playerlist.common.base;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;

import java.io.Serializable;

@EFragment
public class BaseFragment extends Fragment implements Serializable {

    public String getFragmentId() {
        return this.getClass().getSimpleName();
    }

    protected void showLoader() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoader();
        }
    }

    protected void hideLoader() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideLoader();
        }
    }
}
