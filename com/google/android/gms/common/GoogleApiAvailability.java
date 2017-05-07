// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.Intent;
import android.content.Context;

public class GoogleApiAvailability
{
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability zzYk;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        zzYk = new GoogleApiAvailability();
    }
    
    public static GoogleApiAvailability getInstance() {
        return GoogleApiAvailability.zzYk;
    }
    
    public int isGooglePlayServicesAvailable(final Context context) {
        int googlePlayServicesAvailable;
        if (GooglePlayServicesUtil.zzd(context, googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context))) {
            googlePlayServicesAvailable = 18;
        }
        return googlePlayServicesAvailable;
    }
    
    public void zzac(final Context context) {
        GooglePlayServicesUtil.zzac(context);
    }
    
    public Intent zzbb(final int n) {
        return GooglePlayServicesUtil.zzbc(n);
    }
    
    public boolean zzd(final Context context, final int n) {
        return GooglePlayServicesUtil.zzd(context, n);
    }
}
