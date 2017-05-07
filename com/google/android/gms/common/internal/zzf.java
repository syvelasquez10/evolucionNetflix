// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import android.content.res.Resources;
import com.google.android.gms.internal.zzle;
import com.google.android.gms.R$string;
import android.content.Context;

public final class zzf
{
    public static String zzb(final Context context, final int n, final String s) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(R$string.common_google_play_services_unknown_issue);
            }
            case 1: {
                if (zzle.zzb(resources)) {
                    return resources.getString(R$string.common_google_play_services_install_text_tablet, new Object[] { s });
                }
                return resources.getString(R$string.common_google_play_services_install_text_phone, new Object[] { s });
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_text, new Object[] { s });
            }
            case 18: {
                return resources.getString(R$string.common_google_play_services_updating_text, new Object[] { s });
            }
            case 2: {
                return resources.getString(R$string.common_google_play_services_update_text, new Object[] { s });
            }
            case 42: {
                return resources.getString(R$string.common_android_wear_update_text, new Object[] { s });
            }
            case 9: {
                return resources.getString(R$string.common_google_play_services_unsupported_text, new Object[] { s });
            }
            case 7: {
                return resources.getString(R$string.common_google_play_services_network_error_text);
            }
            case 5: {
                return resources.getString(R$string.common_google_play_services_invalid_account_text);
            }
            case 16: {
                return resources.getString(R$string.common_google_play_services_api_unavailable_text, new Object[] { s });
            }
            case 17: {
                return resources.getString(R$string.common_google_play_services_sign_in_failed_text);
            }
        }
    }
    
    public static final String zzg(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                Log.e("GooglePlayServicesUtil", "Unexpected error code " + n);
                return null;
            }
            case 4:
            case 6: {
                return null;
            }
            case 1: {
                return resources.getString(R$string.common_google_play_services_install_title);
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_title);
            }
            case 18: {
                return resources.getString(R$string.common_google_play_services_updating_title);
            }
            case 2: {
                return resources.getString(R$string.common_google_play_services_update_title);
            }
            case 42: {
                return resources.getString(R$string.common_android_wear_update_title);
            }
            case 9: {
                Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                return resources.getString(R$string.common_google_play_services_unsupported_title);
            }
            case 7: {
                Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                return resources.getString(R$string.common_google_play_services_network_error_title);
            }
            case 8: {
                Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                return null;
            }
            case 10: {
                Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                return null;
            }
            case 5: {
                Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                return resources.getString(R$string.common_google_play_services_invalid_account_title);
            }
            case 11: {
                Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                return null;
            }
            case 16: {
                Log.e("GooglePlayServicesUtil", "One of the API components you attempted to connect to is not available.");
                return null;
            }
            case 17: {
                Log.e("GooglePlayServicesUtil", "The specified account could not be signed in.");
                return resources.getString(R$string.common_google_play_services_sign_in_failed_title);
            }
        }
    }
    
    public static String zzh(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(17039370);
            }
            case 1: {
                return resources.getString(R$string.common_google_play_services_install_button);
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_button);
            }
            case 2:
            case 42: {
                return resources.getString(R$string.common_google_play_services_update_button);
            }
        }
    }
}
