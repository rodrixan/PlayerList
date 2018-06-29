package com.projects.rodrixan.playerlist.common.view;

import android.content.Context;

public interface BaseView {
    Context getViewContext();
    void onServiceError(String message);
}
