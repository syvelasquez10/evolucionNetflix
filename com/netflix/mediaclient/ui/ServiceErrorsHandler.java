// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
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
            builder.setMessage(2131492986);
            builder.setCancelable(false);
            builder.setNegativeButton(2131493126, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, int intPref) {
                    intPref = PreferenceUtils.getIntPref((Context)activity, "config_recommended_version", -1);
                    if (Log.isLoggable("ServiceErrorsHandler", 4)) {
                        Log.i("ServiceErrorsHandler", "User clicked cancel on prompt to update. Saving minRecommendedVersion = " + intPref);
                    }
                    PreferenceUtils.putIntPref((Context)activity, "nflx_update_skipped", intPref);
                    activity.startActivity(RelaunchActivity.createStartIntent(activity, "ServiceErrorsHandler"));
                    activity.finish();
                }
            });
        }
        else {
            builder.setMessage(2131492987);
        }
        builder.setPositiveButton(2131492982, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
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
    
    public static boolean handleManagerResponse(final Activity activity, final Status status) {
        boolean b = false;
        final StatusCode statusCode = status.getStatusCode();
        Log.v("ServiceErrorsHandler", "Handling manager response, code: " + statusCode + " [" + activity.getClass().toString() + "]");
        switch (statusCode) {
            default: {
                provideDialog(activity, activity.getString(2131493278) + " (" + statusCode.getValue() + ")");
                b = true;
                return b;
            }
            case OK: {
                return b;
            }
            case NON_RECOMMENDED_APP_VERSION: {
                return handleAppUpdateNeeded(activity, false);
            }
            case OBSOLETE_APP_VERSION: {
                return handleAppUpdateNeeded(activity, true);
            }
            case NO_CONNECTIVITY: {
                provideDialog(activity, activity.getString(2131493139));
                return true;
            }
            case DRM_FAILURE_CDM:
            case DRM_FAILURE_GOOGLE_CDM_PROVISIONG_DENIED: {
                provideDialog(activity, activity.getString(2131493251));
                return true;
            }
            case HTTP_SSL_DATE_TIME_ERROR:
            case HTTP_SSL_ERROR:
            case HTTP_SSL_NO_VALID_CERT: {
                provideDialog(activity, activity.getString(2131493277) + " (" + statusCode.getValue() + ")");
                return true;
            }
        }
    }
    
    private static void provideDialog(final Activity activity, final String message) {
        new AlertDialog$Builder((Context)activity).setCancelable(false).setMessage((CharSequence)message).setPositiveButton(2131492982, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                activity.finish();
            }
        }).show();
    }
}
