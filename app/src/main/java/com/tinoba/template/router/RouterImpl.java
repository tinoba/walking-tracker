package com.tinoba.template.router;

import android.app.Activity;
import androidx.fragment.app.FragmentManager;

public final class RouterImpl implements Router {

    private static final int LAST_FRAGMENT = 0;
    private static final int NO_PENDING_TRANSITION = 0;

    private final Activity activity;
    private final FragmentManager fragmentManager;

    public RouterImpl(final Activity activity, final FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    private void closeScreen() {
        activity.finish();
        activity.overridePendingTransition(NO_PENDING_TRANSITION, NO_PENDING_TRANSITION);
    }

    @Override
    public void goBack() {
        if (fragmentManager.getBackStackEntryCount() == LAST_FRAGMENT) {
            closeScreen();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
