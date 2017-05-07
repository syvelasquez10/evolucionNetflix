// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import android.view.View;
import android.view.View$OnClickListener;
import java.io.Serializable;
import com.netflix.mediaclient.service.ServiceAgent;
import android.view.MenuItem;
import android.view.Menu;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.UserProfile;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import android.app.Activity;

public class KidsUtils
{
    private static final String TAG = "KidsUtils";
    
    private static boolean accountHasKidsProfile(final NetflixActivity netflixActivity) {
        if (netflixActivity == null) {
            Log.w("KidsUtils", "Null activity - can't get profiles");
        }
        else {
            if (netflixActivity.getServiceManager() == null) {
                Log.w("KidsUtils", "Null service man - can't get profiles");
                return false;
            }
            if (netflixActivity.getServiceManager().getAllProfiles() == null) {
                Log.w("KidsUtils", "Null profiles list - can't get profiles");
                return false;
            }
            final Iterator<? extends UserProfile> iterator = netflixActivity.getServiceManager().getAllProfiles().iterator();
            while (iterator.hasNext()) {
                if (((UserProfile)iterator.next()).isKidsProfile()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static int computeRowHeight(final NetflixActivity netflixActivity, final boolean b) {
        final int computeViewPagerWidth;
        final int n = computeViewPagerWidth = BasePaginatedAdapter.computeViewPagerWidth((Context)netflixActivity, b);
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (serviceManager == null) {
            Log.w("KidsUtils", "Null service manager in computeRowHeight()");
            return computeViewPagerWidth;
        }
        int n2 = computeViewPagerWidth;
        if (serviceManager.getConfiguration().getKidsOnPhoneConfiguration().getLolomoImageType() == KidsOnPhoneConfiguration.LolomoImageType.HORIZONTAL) {
            n2 = (int)(computeViewPagerWidth * 0.5625f);
        }
        if (Log.isLoggable("KidsUtils", 2)) {
            Log.v("KidsUtils", "Computed row height as: " + n2 + ", from width of: " + n);
        }
        return n2;
    }
    
    public static Intent createExitKidsIntent(final Activity activity) {
        return ProfileSelectionActivity.createStartIntent(activity);
    }
    
    public static MenuItem createKidsMenuItem(final NetflixActivity netflixActivity, final Menu menu) {
        final MenuItem add = menu.add(0, 2131165242, 0, 2131492956);
        updateKidsMenuItem(netflixActivity, add);
        return add;
    }
    
    private static Intent createSwitchToKidsIntent(final Activity activity) {
        return ProfileSelectionActivity.createSwitchToKidsIntent(activity);
    }
    
    public static boolean isKidsProfile(final UserProfile userProfile) {
        return userProfile != null && userProfile.isKidsProfile();
    }
    
    public static boolean isKidsWithUpDownScrolling(final NetflixActivity netflixActivity) {
        return netflixActivity.isForKids() && netflixActivity.getServiceManager().getConfiguration().getKidsOnPhoneConfiguration().getScrollBehavior() == KidsOnPhoneConfiguration.ScrollBehavior.UP_DOWN;
    }
    
    public static boolean isKoPExperience(final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final UserProfile userProfile) {
        return isKidsProfile(userProfile) && isUserInKopTest(configurationAgentInterface);
    }
    
    public static boolean isUserInKopTest(final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface) {
        return configurationAgentInterface != null && configurationAgentInterface.getKidsOnPhoneConfiguration() != null && configurationAgentInterface.getKidsOnPhoneConfiguration().isInKidsOnPhoneTest();
    }
    
    public static boolean shouldShowHorizontalImages(final NetflixActivity netflixActivity) {
        return netflixActivity.getServiceManager().getConfiguration().getKidsOnPhoneConfiguration().getLolomoImageType() == KidsOnPhoneConfiguration.LolomoImageType.HORIZONTAL;
    }
    
    private static boolean shouldShowKidsEntryInActionBar(final NetflixActivity netflixActivity) {
        return accountHasKidsProfile(netflixActivity) && netflixActivity.getServiceManager().getConfiguration().getKidsOnPhoneConfiguration().shouldShowKidsEntryInActionBar();
    }
    
    public static boolean shouldShowKidsEntryInGenreLomo(final NetflixActivity netflixActivity) {
        return accountHasKidsProfile(netflixActivity) && netflixActivity.getServiceManager().getConfiguration().getKidsOnPhoneConfiguration().shouldShowKidsEntryInGenreLomo();
    }
    
    public static boolean shouldShowKidsEntryInMenu(final NetflixActivity netflixActivity) {
        return accountHasKidsProfile(netflixActivity) && netflixActivity.getServiceManager().getConfiguration().getKidsOnPhoneConfiguration().shouldShowKidsEntryInMenu();
    }
    
    public static boolean shouldShowKidsExperience(final NetflixActivity netflixActivity) {
        if (netflixActivity.isForKids()) {
            Log.v("KidsUtils", "Should show kids experience because we're in a kids activity");
            return true;
        }
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        final boolean b = serviceManager != null && serviceManager.getCurrentProfile().isKidsProfile() && serviceManager.getConfiguration() != null && serviceManager.getConfiguration().getKidsOnPhoneConfiguration().isKidsOnPhoneEnabled();
        if (Log.isLoggable("KidsUtils", 2)) {
            Serializable value;
            if (serviceManager == null) {
                value = "null service";
            }
            else if (serviceManager.getCurrentProfile() == null) {
                value = "null profile";
            }
            else {
                value = serviceManager.getCurrentProfile().isKidsProfile();
            }
            Serializable value2;
            if (serviceManager.getConfiguration() == null) {
                value2 = "null config";
            }
            else {
                value2 = serviceManager.getConfiguration().getKidsOnPhoneConfiguration().isKidsOnPhoneEnabled();
            }
            Log.v("KidsUtils", String.format("Should show kids experience - isKidsProfile: %s, KoP enabled: %s, rtn: %s", value, value2, b));
        }
        return b;
    }
    
    public static void updateKidsMenuItem(final NetflixActivity netflixActivity, final MenuItem menuItem) {
        if (menuItem == null) {
            return;
        }
        if (!netflixActivity.getServiceManager().isReady() || !shouldShowKidsEntryInActionBar(netflixActivity)) {
            menuItem.setVisible(false).setEnabled(false);
            return;
        }
        menuItem.setVisible(true).setEnabled(true);
        if (netflixActivity.isForKids()) {
            menuItem.setTitle(2131492961).setIntent(createExitKidsIntent(netflixActivity)).setShowAsAction(2);
            return;
        }
        menuItem.setTitle(2131492948).setIcon(2130837723).setIntent(createSwitchToKidsIntent(netflixActivity)).setShowAsAction(2);
    }
    
    public static class OnSwitchToKidsClickListener implements View$OnClickListener
    {
        private final Activity activity;
        
        public OnSwitchToKidsClickListener(final Activity activity) {
            this.activity = activity;
        }
        
        public void onClick(final View view) {
            this.activity.startActivity(createSwitchToKidsIntent(this.activity));
        }
    }
}
