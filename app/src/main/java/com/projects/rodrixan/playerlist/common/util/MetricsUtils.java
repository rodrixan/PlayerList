package com.projects.rodrixan.playerlist.common.util;

import android.content.Context;
import android.util.TypedValue;

public final class MetricsUtils {

    private MetricsUtils() {
    }

    public static int convertDipToPixels(Context context, int dip) {
        if (context == null || dip < 0) {
            return -1;
        }
        else if (dip == 0) {
            return 0;
        }
        else {
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                    context.getResources().getDisplayMetrics()));
        }
    }
}
