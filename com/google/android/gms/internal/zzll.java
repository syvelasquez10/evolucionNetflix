// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import android.content.BroadcastReceiver;

abstract class zzll extends BroadcastReceiver
{
    protected Context mContext;
    
    public static <T extends zzll> T zza(final Context context, final T t) {
        return zza(context, t, GoogleApiAvailability.getInstance());
    }
    
    public static <T extends zzll> T zza(final Context mContext, final T t, final GoogleApiAvailability googleApiAvailability) {
        final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        mContext.registerReceiver((BroadcastReceiver)t, intentFilter);
        t.mContext = mContext;
        zzll zzll = t;
        if (!googleApiAvailability.zzj(mContext, "com.google.android.gms")) {
            t.zzoi();
            t.unregister();
            zzll = null;
        }
        return (T)zzll;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        Object schemeSpecificPart = null;
        if (data != null) {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(schemeSpecificPart)) {
            this.zzoi();
            this.unregister();
        }
    }
    
    public void unregister() {
        synchronized (this) {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver((BroadcastReceiver)this);
            }
            this.mContext = null;
        }
    }
    
    protected abstract void zzoi();
}
