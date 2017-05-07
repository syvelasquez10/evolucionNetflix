// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import java.util.Iterator;
import android.content.IntentFilter;
import java.util.Set;

public final class UserAgentBroadcastIntents
{
    public static final String EXTRA_USER_PROFILE_SELECTION_RESULT_INT = "com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_INT";
    public static final String EXTRA_USER_PROFILE_SELECTION_RESULT_STRING = "com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_STRING";
    public static final String NOTIFY_CURRENT_PROFILE_INVALID = "com.netflix.mediaclient.intent.action.NOTIFY_CURRENT_PROFILE_INVALID";
    public static final String NOTIFY_PROFILES_LIST_UPDATED = "com.netflix.mediaclient.intent.action.NOTIFY_PROFILES_LIST_UPDATED";
    public static final String NOTIFY_USER_ACCOUNT_ACTIVE = "com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE";
    public static final String NOTIFY_USER_ACCOUNT_DEACTIVE = "com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE";
    public static final String NOTIFY_USER_PROFILE_ACTIVE = "com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE";
    public static final String NOTIFY_USER_PROFILE_DEACTIVE = "com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE";
    public static final String NOTIFY_USER_PROFILE_READY_TO_SELECT = "com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_READY_TO_SELECT";
    public static final String NOTIFY_USER_PROFILE_SELECTION_RESULT = "com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_SELECTION_RESULT";
    private static final Set<String> notifSet;
    
    static {
        notifSet = new UserAgentBroadcastIntents$1();
    }
    
    public static IntentFilter getNotificationIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        final Iterator<String> iterator = UserAgentBroadcastIntents.notifSet.iterator();
        while (iterator.hasNext()) {
            intentFilter.addAction((String)iterator.next());
        }
        return intentFilter;
    }
    
    static void signalProfileActive(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE"));
    }
    
    static void signalProfileDeactivated(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE"));
    }
    
    static void signalProfileInvalid(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_CURRENT_PROFILE_INVALID"));
    }
    
    static void signalProfileReady2Select(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_READY_TO_SELECT"));
    }
    
    static void signalProfileSelectionResult(final Context context, final int n, final String s) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_SELECTION_RESULT");
        intent.putExtra("com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_INT", n);
        if (StringUtils.isNotEmpty(s)) {
            intent.putExtra("com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_STRING", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    static void signalProfilesListUpdated(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_PROFILES_LIST_UPDATED"));
    }
    
    static void signalUserAccountActive(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_ACTIVE"));
    }
    
    static void signalUserAccountDeactivated(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE"));
    }
}
