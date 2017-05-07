// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.app.AlertDialog$Builder;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import com.netflix.mediaclient.util.AppStoreHelper;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;

public class ServiceErrorsHandler
{
    private static final String TAG = "ServiceErrorsHandler";
    
    private static boolean handleAppUpdateNeeded(final Activity activity, final boolean b) {
        if (!b) {
            final int intPref = PreferenceUtils.getIntPref((Context)activity, "nflx_update_skipped", 0);
            final int intPref2 = PreferenceUtils.getIntPref((Context)activity, "config_recommended_version", -1);
            if (Log.isLoggable("ServiceErrorsHandler", 2)) {
                Log.v("ServiceErrorsHandler", "Current min recommended version = " + intPref2 + " - Last skipped update = " + intPref);
            }
            if (intPref == intPref2) {
                return false;
            }
        }
        final UpdateDialog.Builder builder = new UpdateDialog.Builder((Context)activity);
        builder.setTitle("");
        if (!b) {
            builder.setMessage(2131296351);
            builder.setCancelable(false);
            builder.setNegativeButton(2131296494, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, int intPref) {
                    intPref = PreferenceUtils.getIntPref((Context)activity, "config_recommended_version", -1);
                    if (Log.isLoggable("ServiceErrorsHandler", 4)) {
                        Log.i("ServiceErrorsHandler", "User clicked cancel on prompt to update. Saving minRecommendedVersion = " + intPref);
                    }
                    PreferenceUtils.putIntPref((Context)activity, "nflx_update_skipped", intPref);
                    activity.startActivity(LaunchActivity.createStartIntent(activity, "ServiceErrorsHandler"));
                    activity.finish();
                }
            });
        }
        else {
            builder.setMessage(2131296352);
        }
        builder.setPositiveButton(2131296347, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                Log.i("ServiceErrorsHandler", "User clicked Ok on prompt to update");
                final Intent updateSourceIntent = AppStoreHelper.getUpdateSourceIntent((Context)activity);
                if (updateSourceIntent == null) {
                    return;
                }
                updateSourceIntent.addFlags(268435456);
                try {
                    activity.startActivity(updateSourceIntent);
                }
                catch (ActivityNotFoundException ex) {
                    Log.e("ServiceErrorsHandler", "Failed to start store Activity!", (Throwable)ex);
                }
                finally {
                    activity.finish();
                }
            }
        });
        builder.show();
        return true;
    }
    
    public static boolean handleManagerResponse(final Activity activity, final int n) {
        boolean b = false;
        Log.v("ServiceErrorsHandler", "Handling manager response, code: " + n + " [" + activity.getClass().toString() + "]");
        switch (n) {
            default: {
                provideDialog(activity, activity.getString(2131296644) + " (" + n + ")");
                b = true;
                return b;
            }
            case 0: {
                return b;
            }
            case 1: {
                return handleAppUpdateNeeded(activity, false);
            }
            case -5: {
                return handleAppUpdateNeeded(activity, true);
            }
            case -11: {
                provideDialog(activity, activity.getString(2131296507));
                return true;
            }
            case -101:
            case -100: {
                provideDialog(activity, activity.getString(2131296617));
                return true;
            }
        }
    }
    
    private static void provideDialog(final Activity activity, final String message) {
        new AlertDialog$Builder((Context)activity).setCancelable(false).setMessage((CharSequence)message).setPositiveButton(2131296347, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                activity.finish();
            }
        }).show();
    }
}
