// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.view.View$OnClickListener;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class DataSaverNotifier
{
    private static final String TAG = "DataSaverNotifier";
    
    private static void markPersistentStateAsShown(final Context context) {
        PreferenceUtils.putBooleanPref(context, "should_show_ftc_data_saver_notification", false);
    }
    
    private static boolean shouldShowNotification(final Context context) {
        final boolean networkTypeCellular = ConnectivityUtils.isNetworkTypeCellular(context);
        final boolean booleanPref = PreferenceUtils.getBooleanPref(context, "should_show_ftc_data_saver_notification", true);
        final boolean b = networkTypeCellular && booleanPref;
        if (Log.isLoggable()) {
            Log.v("DataSaverNotifier", String.format("isOnCellular: %b, shouldShowFromPrefs: %b, shouldShowNotif: %b", networkTypeCellular, booleanPref, b));
        }
        return b;
    }
    
    private static void showNotification(final Activity activity) {
        Snackbar.make(activity.findViewById(16908290), 2131230926, 0).setAction(2131231182, (View$OnClickListener)new DataSaverNotifier$AppSettingsClickListener(activity)).show();
    }
    
    public static void showNotificationIfNecessary(final Activity activity) {
        if (shouldShowNotification((Context)activity)) {
            showNotification(activity);
            markPersistentStateAsShown((Context)activity);
        }
    }
}
