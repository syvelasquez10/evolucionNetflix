// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.Bundle;
import android.os.IBinder;
import android.content.Context;
import com.google.android.gms.internal.zzgk;

@zzgk
public class zzb
{
    private final Context mContext;
    Object zzBM;
    private final boolean zzBN;
    
    public zzb(final Context context) {
        this(context, true);
    }
    
    public zzb(final Context mContext, final boolean zzBN) {
        this.mContext = mContext;
        this.zzBN = zzBN;
    }
    
    public void destroy() {
        this.zzBM = null;
    }
    
    public void zzM(final IBinder binder) {
        try {
            this.zzBM = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, binder);
        }
        catch (Exception ex) {
            if (this.zzBN) {
                com.google.android.gms.ads.internal.util.client.zzb.zzaE("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
            }
        }
    }
    
    public Bundle zzb(final String s, final String s2, final String s3) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle)loadClass.getDeclaredMethod("getBuyIntent", Integer.TYPE, String.class, String.class, String.class, String.class).invoke(loadClass.cast(this.zzBM), 3, s, s2, "inapp", s3);
        }
        catch (Exception ex) {
            if (this.zzBN) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            }
            return null;
        }
    }
}
