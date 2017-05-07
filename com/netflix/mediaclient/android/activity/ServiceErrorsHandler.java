// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.text.Spannable;
import android.text.util.Linkify;
import android.text.SpannableString;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.repository.UserLocale;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import com.netflix.mediaclient.util.AndroidUtils;
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
            updateDialog$Builder.setMessage(2131165406);
            updateDialog$Builder.setCancelable(false);
            updateDialog$Builder.setNegativeButton(2131165413, (DialogInterface$OnClickListener)new ServiceErrorsHandler$1(activity));
        }
        else {
            updateDialog$Builder.setMessage(2131165516);
        }
        updateDialog$Builder.setPositiveButton(2131165543, (DialogInterface$OnClickListener)new ServiceErrorsHandler$2(activity));
        updateDialog$Builder.show();
        return true;
    }
    
    public static boolean handleManagerResponse(final Activity activity, final Status status) {
        final StatusCode statusCode = status.getStatusCode();
        Log.v("ServiceErrorsHandler", "Handling manager response, code: " + statusCode + " [" + activity.getClass().toString() + "]");
        switch (ServiceErrorsHandler$5.$SwitchMap$com$netflix$mediaclient$StatusCode[statusCode.ordinal()]) {
            default: {
                provideDialog(activity, activity.getString(2131165637) + " (" + statusCode.getValue() + ")");
                return true;
            }
            case 1: {
                return false;
            }
            case 2: {
                return handleAppUpdateNeeded(activity, false);
            }
            case 3: {
                if (AndroidUtils.getAndroidVersion() > 18) {
                    return false;
                }
                final ServiceManager serviceManager = ((NetflixActivity)activity).getServiceManager();
                if (serviceManager == null) {
                    Log.d("ServiceErrorsHandler", "nf_config_locale manager == null");
                    return false;
                }
                if (!serviceManager.isApkMissingSupportForLocale()) {
                    return false;
                }
                final Locale default1 = Locale.getDefault();
                Log.d("ServiceErrorsHandler", "locale: " + default1);
                final UserLocale alertLocale = serviceManager.getConfiguration().getAlertLocale(default1.getLanguage());
                if (alertLocale == null || StringUtils.isEmpty(alertLocale.getLanguageDescription())) {
                    Log.d("ServiceErrorsHandler", "AlertLocale is null");
                    return false;
                }
                return provideUnSupportedLanguageDialog(activity, String.format(activity.getString(2131165725), alertLocale.getLanguageDescription()), alertLocale);
            }
            case 4: {
                return handleAppUpdateNeeded(activity, true);
            }
            case 5: {
                provideDialog(activity, activity.getString(2131165611));
                return true;
            }
            case 6:
            case 7: {
                provideDialog(activity, activity.getString(2131165485));
                return true;
            }
            case 8:
            case 9:
            case 10: {
                provideDialog(activity, activity.getString(2131165631) + " (" + statusCode.getValue() + ")");
                return true;
            }
        }
    }
    
    private static void provideDialog(final Activity activity, final String message) {
        new AlertDialog$Builder((Context)activity).setCancelable(false).setMessage((CharSequence)message).setPositiveButton(2131165543, (DialogInterface$OnClickListener)new ServiceErrorsHandler$3(activity)).show();
    }
    
    private static boolean provideUnSupportedLanguageDialog(final Activity activity, final String s, final UserLocale userLocale) {
        final SpannableString message = new SpannableString((CharSequence)s);
        Linkify.addLinks((Spannable)message, 15);
        final AlertDialog create = new AlertDialog$Builder((Context)activity).setCancelable(false).setMessage((CharSequence)message).setPositiveButton(2131165718, (DialogInterface$OnClickListener)new ServiceErrorsHandler$4(activity, userLocale)).create();
        create.show();
        final TextView textView = (TextView)create.findViewById(16908299);
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        return true;
    }
}
