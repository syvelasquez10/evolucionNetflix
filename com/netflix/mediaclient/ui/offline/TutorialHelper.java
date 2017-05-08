// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.view.ViewTreeObserver$OnPreDrawListener;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.SharedPreferences$Editor;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.android.tooltips.Tooltip$Callback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.design.widget.CoordinatorLayout;
import android.app.Activity;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.content.Context;

public class TutorialHelper
{
    public static final TutorialHelper EMPTY;
    private static final String PREFS = "com.netflix.android.fullscreen_tutorial";
    public static final String TAG = "TutorialHelper";
    private static final String USER_TUTORIAL_DOWNLOAD_BUTTON = "USER_TUTORIAL_DOWNLOAD_BUTTON";
    private static final String USER_TUTORIAL_DOWNLOAD_GENRE = "USER_TUTORIAL_DOWNLOADS_GENRE";
    public static final String USER_TUTORIAL_FULLSCREEN = "USER_TUTORIAL_FULLSCREEN";
    private static final String USER_TUTORIAL_MYDOWNLOADS_BUTTON = "USER_TUTORIAL_MY_DOWNLOADS_BUTTON";
    Context context;
    UserProfile profile;
    Tooltip tooltip;
    
    static {
        EMPTY = new TutorialHelper(null, null);
    }
    
    public TutorialHelper(final Context context, final UserProfile profile) {
        this.context = context;
        this.profile = profile;
    }
    
    public static Tooltip buildDownloadButtonTutorial(final View view, final Activity activity, final UserProfile userProfile) {
        if (view == null || activity == null || userProfile == null) {
            return null;
        }
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)activity.findViewById(2131755332);
        if (coordinatorLayout != null) {
            final Tooltip tooltip = Tooltip.makeTooltip((Context)activity, coordinatorLayout, view, 2131296939, 2131296934);
            tooltip.setOneTime(getTutorialPrefKey("USER_TUTORIAL_DOWNLOAD_BUTTON", userProfile));
            return tooltip;
        }
        return null;
    }
    
    public static Tooltip buildMyDownloadTutorial(final View view, final Activity activity, final UserProfile userProfile) {
        if (view == null || activity == null || userProfile == null) {
            return null;
        }
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)activity.findViewById(2131755332);
        if (coordinatorLayout != null) {
            final Tooltip tooltip = Tooltip.makeTooltip((Context)activity, coordinatorLayout, view, 2131296939, 2131296938);
            tooltip.setOneTime(getTutorialPrefKey("USER_TUTORIAL_MY_DOWNLOADS_BUTTON", userProfile));
            return tooltip;
        }
        return null;
    }
    
    public static void clearPrefs(final Context context) {
        context.getSharedPreferences("com.netflix.android.fullscreen_tutorial", 0).edit().clear().apply();
    }
    
    private void createTooltip(final TutorialHelper$Tutorialable tutorialHelper$Tutorialable) {
        boolean b = true;
        boolean b2;
        if (this.tooltip == null) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (tutorialHelper$Tutorialable == null) {
            b = false;
        }
        if ((b & b2) && this.profile != null) {
            this.tooltip = tutorialHelper$Tutorialable.setupTutorial(this.profile);
        }
        this.dismissTutorial();
    }
    
    private static String getTutorialPrefKey(final String s, final UserProfile userProfile) {
        return s + "_" + userProfile.getProfileGuid();
    }
    
    public static boolean isOfflineFeatureEnabled(final ServiceManager serviceManager) {
        return serviceManager != null && serviceManager.isOfflineFeatureAvailable();
    }
    
    private void scrollToDownloadButton(final RecyclerView recyclerView, final View view) {
        this.tooltip.setCallback(new TutorialHelper$2(this, recyclerView));
        final int n = ViewUtils.getLocationOnScreen(view).bottom - DeviceUtils.getScreenHeightInPixels(this.context);
        if (n > 0) {
            recyclerView.setOnScrollListener(new TutorialHelper$3(this, recyclerView));
            recyclerView.smoothScrollBy(0, n + this.context.getResources().getDimensionPixelSize(2131427838));
            return;
        }
        this.tooltip.show();
    }
    
    private void scrollToFindDownloadButton(final RecyclerView recyclerView, final TutorialHelper$Tutorialable tutorialHelper$Tutorialable) {
        if (recyclerView.getAdapter().getItemCount() > 0) {
            recyclerView.setOnScrollListener(new TutorialHelper$1(this, recyclerView, tutorialHelper$Tutorialable));
            if (!Tooltip.isConsumed(recyclerView.getContext(), getTutorialPrefKey("USER_TUTORIAL_DOWNLOAD_BUTTON", this.profile))) {
                recyclerView.smoothScrollToPosition(1);
            }
        }
    }
    
    public void dismissTutorial() {
        if (this.tooltip != null) {
            this.tooltip.dismiss();
        }
    }
    
    public void setFullscreenTutorialDisplayed(final boolean b) {
        boolean b2 = false;
        if (this.context != null && this.profile != null) {
            final SharedPreferences$Editor edit = this.context.getSharedPreferences("com.netflix.android.fullscreen_tutorial", 0).edit();
            final String tutorialPrefKey = getTutorialPrefKey("USER_TUTORIAL_FULLSCREEN", this.profile);
            if (!b) {
                b2 = true;
            }
            edit.putBoolean(tutorialPrefKey, b2).apply();
        }
    }
    
    public boolean shouldDisplayFullscreenTutorial(final ServiceManager serviceManager) {
        boolean b = true;
        if (this.context == null || this.profile == null) {
            b = false;
        }
        else if (!NetflixActivity.isTutorialOn() || !PersistentConfig.isLaunchTutorial(this.context) || !isOfflineFeatureEnabled(serviceManager) || !this.context.getSharedPreferences("com.netflix.android.fullscreen_tutorial", 0).getBoolean(getTutorialPrefKey("USER_TUTORIAL_FULLSCREEN", this.profile), true)) {
            return false;
        }
        return b;
    }
    
    public void showTutorial(final TutorialHelper$Tutorialable tutorialHelper$Tutorialable, final ServiceManager serviceManager) {
        if (NetflixActivity.isTutorialOn() && PersistentConfig.isGuidanceTutorial(this.context) && isOfflineFeatureEnabled(serviceManager)) {
            this.createTooltip(tutorialHelper$Tutorialable);
            if (this.tooltip != null) {
                this.tooltip.show();
            }
        }
    }
    
    public void showTutorialForVideoWithScroll(final TutorialHelper$Tutorialable tutorialHelper$Tutorialable, final VideoDetails videoDetails, final RecyclerView recyclerView, final ServiceManager serviceManager) {
        if (!NetflixActivity.isTutorialOn() || this.profile == null || !PersistentConfig.isGuidanceTutorial(this.context) || videoDetails == null || videoDetails.getPlayable() == null || !videoDetails.getPlayable().isAvailableOffline() || !isOfflineFeatureEnabled(serviceManager) || Tooltip.isConsumed(recyclerView.getContext(), getTutorialPrefKey("USER_TUTORIAL_DOWNLOAD_BUTTON", this.profile))) {
            return;
        }
        recyclerView.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new TutorialHelper$4(this, recyclerView, tutorialHelper$Tutorialable));
    }
}
