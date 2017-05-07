// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsActivity;
import android.app.NotificationManager;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.ui.details.RecommendToFriendsFrag;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.Set;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SocialNotificationsUtils
{
    public static final int SOCIAL_NOTIFICATIONS_ID = 1000;
    public static final String SWIPED_SOCIAL_NOTIFICATION_ID = "swiped_social_notification_id";
    
    public static MenuItem addSocialNotificationsIconIfNeeded(final NetflixActivity netflixActivity, final Menu menu) {
        MenuItem add = null;
        if (isSocialNotificationsFeatureSupported(netflixActivity)) {
            add = menu.add(0, 2131165248, 0, 2131493178);
            add.setIcon(2130837663).setShowAsAction(1);
        }
        return add;
    }
    
    public static <T> void castArrayToSet(final Parcelable[] array, final Set<T> set) {
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add((T)array[i]);
        }
    }
    
    public static void handleNotificationsUpdateReceiver(final Intent intent, final MenuItem menuItem, final String s) {
        Log.i(s, "Recieved social notifications list updated intent");
        if (intent == null) {
            Log.w(s, "Received null intent");
        }
        else {
            final String action = intent.getAction();
            if (Log.isLoggable(s, 2)) {
                Log.v(s, "notificationsUpdateReciever inovoked with Action: " + action);
            }
            if ("com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED".equals(action)) {
                if (menuItem != null) {
                    Log.i(s, "Updating menu icon");
                    updateNotificationsIcon(menuItem, intent.getBooleanExtra("notifications_list_has_unread", false));
                    return;
                }
                Log.e(s, "Social notifications list was updated but notificationsMenuItem is null");
            }
        }
    }
    
    public static void ifSocialNotificationWasCanceledUpdateItsStatus(final Context context, final Intent intent, final String s) {
        final String stringExtra = intent.getStringExtra("swiped_social_notification_id");
        if (!StringUtils.isEmpty(stringExtra)) {
            Log.i(s, "saving swiped out notification id to preferences");
            PreferenceUtils.putStringPref(context, "notification_id_deleted_from_statusbar", stringExtra);
        }
    }
    
    public static boolean isSocialNotificationsFeatureSupported(final NetflixActivity netflixActivity) {
        return netflixActivity != null && isSocialNotificationsFeatureSupported(netflixActivity.getServiceManager());
    }
    
    public static boolean isSocialNotificationsFeatureSupported(final ServiceManager serviceManager) {
        return RecommendToFriendsFrag.isSocialRecommendationsFeatureSupported(serviceManager) && serviceManager.getCurrentProfile().isSocialConnected();
    }
    
    public static boolean isSocialNotificationsFeatureSupported(final UserProfile userProfile, final Context context) {
        return RecommendToFriendsFrag.isSocialRecommendationsFeatureSupported(userProfile, context) && userProfile.isSocialConnected();
    }
    
    public static void removeSocialNotificationsFromStatusBar(final Context context) {
        ((NotificationManager)context.getSystemService("notification")).cancel(1000);
    }
    
    public static boolean tryHandleMenuItemClick(final MenuItem menuItem, final Context context) {
        if (menuItem.getItemId() == 2131165248) {
            context.startActivity(new Intent(context, (Class)SocialNotificationsActivity.class));
            return true;
        }
        return false;
    }
    
    public static void updateNotificationsIcon(final MenuItem menuItem, final boolean b) {
        int icon;
        if (b) {
            icon = 2130837664;
        }
        else {
            icon = 2130837663;
        }
        menuItem.setIcon(icon);
    }
}
