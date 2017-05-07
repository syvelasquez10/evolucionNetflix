// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import android.view.ViewConfiguration;
import android.graphics.drawable.Drawable;
import android.widget.ListView;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.app.Activity;

public class KidsUtils
{
    private static final int LARGE_DETAIL_PAGE_THRESHOLD_DP = 1024;
    private static final float LIST_VIEW_FRICTION_SCALE_FACTOR = 7.5f;
    
    public static void configureListViewForKids(final ListView listView) {
        listView.setDivider((Drawable)null);
        listView.setFriction(ViewConfiguration.getScrollFriction() * 7.5f);
    }
    
    public static Intent createExitKidsIntent(final Activity activity, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName) {
        return ProfileSelectionActivity.createSwitchFromKidsIntent(activity, uiViewLogging$UIViewCommandName);
    }
    
    private static Intent createSwitchToKidsIntent(final Activity activity, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName) {
        return ProfileSelectionActivity.createSwitchToKidsIntent(activity, uiViewLogging$UIViewCommandName);
    }
    
    public static int getDetailsPageContentWidth(final Context context) {
        if (DeviceUtils.getScreenWidthInDPs(context) >= 1024) {
            return (int)(Math.max(DeviceUtils.getScreenHeightInPixels(context), DeviceUtils.getScreenWidthInPixels(context)) * 0.8f);
        }
        return DeviceUtils.getScreenWidthInPixels(context);
    }
    
    public static boolean isKidsProfile(final UserProfile userProfile) {
        return userProfile != null && userProfile.isKidsProfile();
    }
}
