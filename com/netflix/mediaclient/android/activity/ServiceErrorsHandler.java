// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import android.content.DialogInterface$OnClickListener;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
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
            if (Log.isLoggable()) {
                Log.v("ServiceErrorsHandler", "Current min recommended version = " + intPref2 + " - Last skipped update = " + intPref);
            }
            if (intPref == intPref2) {
                return false;
            }
        }
        final UpdateDialog$Builder updateDialog$Builder = new UpdateDialog$Builder((Context)activity);
        updateDialog$Builder.setTitle("");
        if (!b) {
            updateDialog$Builder.setMessage(2131165393);
            updateDialog$Builder.setCancelable(false);
            updateDialog$Builder.setNegativeButton(2131165400, (DialogInterface$OnClickListener)new ServiceErrorsHandler$1(activity));
        }
        else {
            updateDialog$Builder.setMessage(2131165458);
        }
        updateDialog$Builder.setPositiveButton(2131165485, (DialogInterface$OnClickListener)new ServiceErrorsHandler$2(activity));
        updateDialog$Builder.show();
        return true;
    }
    
    public static boolean handleManagerResponse(final Activity activity, final Status status) {
        boolean b = false;
        final StatusCode statusCode = status.getStatusCode();
        Log.v("ServiceErrorsHandler", "Handling manager response, code: " + statusCode + " [" + activity.getClass().toString() + "]");
        switch (ServiceErrorsHandler$4.$SwitchMap$com$netflix$mediaclient$StatusCode[statusCode.ordinal()]) {
            default: {
                provideDialog(activity, activity.getString(2131165577) + " (" + statusCode.getValue() + ")");
                b = true;
                return b;
            }
            case 1: {
                return b;
            }
            case 2: {
                return handleAppUpdateNeeded(activity, false);
            }
            case 3: {
                return handleAppUpdateNeeded(activity, true);
            }
            case 4: {
                provideDialog(activity, activity.getString(2131165551));
                return true;
            }
            case 5:
            case 6: {
                provideDialog(activity, activity.getString(2131165428));
                return true;
            }
            case 7:
            case 8:
            case 9: {
                provideDialog(activity, activity.getString(2131165571) + " (" + statusCode.getValue() + ")");
                return true;
            }
        }
    }
    
    private static void provideDialog(final Activity activity, final String message) {
        new AlertDialog$Builder((Context)activity).setCancelable(false).setMessage((CharSequence)message).setPositiveButton(2131165485, (DialogInterface$OnClickListener)new ServiceErrorsHandler$3(activity)).show();
    }
}
