package id.gits.core.helper;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by ibun on 15/01/16.
 */
public class ViewHelper {
    public static void startSwipeRefresh(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        }, 100);
    }

}
