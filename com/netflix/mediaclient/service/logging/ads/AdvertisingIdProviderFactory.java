// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

import com.netflix.mediaclient.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;

public final class AdvertisingIdProviderFactory
{
    private static final String TAG = "nf_log";
    
    public static AdvertisingIdProvider getInstance(final Context context) {
        ThreadUtils.assertNotOnMain();
        GooglePlayAdvertisingIdProvider googlePlayAdvertisingIdProvider2;
        try {
            final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "Google Play status: " + googlePlayServicesAvailable);
            }
            if (googlePlayServicesAvailable != 0) {
                Log.e("nf_log", "Not success!");
                return null;
            }
            final GooglePlayAdvertisingIdProvider googlePlayAdvertisingIdProvider = googlePlayAdvertisingIdProvider2 = new GooglePlayAdvertisingIdProvider(context);
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "Google Play Advertising ID: " + googlePlayAdvertisingIdProvider.getId());
                Log.d("nf_log", "Google Play Advertising ID is resettable : " + googlePlayAdvertisingIdProvider.isResettable());
                Log.d("nf_log", "Google Play Advertising ID is limited : " + googlePlayAdvertisingIdProvider.isLimitAdTrackingEnabled());
                return googlePlayAdvertisingIdProvider;
            }
        }
        catch (Throwable t) {
            Log.e("nf_log", "Failed to create Google Play provider", t);
            googlePlayAdvertisingIdProvider2 = null;
        }
        return googlePlayAdvertisingIdProvider2;
    }
}
