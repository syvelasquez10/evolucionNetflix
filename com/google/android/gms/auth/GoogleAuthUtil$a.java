// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Message;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;

class GoogleAuthUtil$a extends Handler
{
    private final Context mD;
    
    GoogleAuthUtil$a(final Context md) {
        Looper looper;
        if (Looper.myLooper() == null) {
            looper = Looper.getMainLooper();
        }
        else {
            looper = Looper.myLooper();
        }
        super(looper);
        this.mD = md;
    }
    
    public void handleMessage(final Message message) {
        if (message.what == 1) {
            final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mD);
            if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesAvailable)) {
                GooglePlayServicesUtil.showErrorNotification(googlePlayServicesAvailable, this.mD);
            }
            return;
        }
        Log.wtf("GoogleAuthUtil", "Don't know how to handle this message: " + message.what);
    }
}
