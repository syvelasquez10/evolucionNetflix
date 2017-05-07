// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Bundle;
import android.content.Context;

@ez
public class dw
{
    private final Context mContext;
    private Object sk;
    
    public dw(final Context mContext) {
        this.mContext = mContext;
    }
    
    public Bundle a(final String s, final String s2, final String s3) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle)loadClass.getDeclaredMethod("getBuyIntent", Integer.TYPE, String.class, String.class, String.class, String.class).invoke(loadClass.cast(this.sk), 3, s, s2, "inapp", s3);
        }
        catch (Exception ex) {
            gs.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            return null;
        }
    }
    
    public int c(final String s, final String s2) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (int)loadClass.getDeclaredMethod("consumePurchase", Integer.TYPE, String.class, String.class).invoke(loadClass.cast(this.sk), 3, s, s2);
        }
        catch (Exception ex) {
            gs.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            return 5;
        }
    }
    
    public Bundle d(final String s, final String s2) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle)loadClass.getDeclaredMethod("getPurchases", Integer.TYPE, String.class, String.class, String.class).invoke(loadClass.cast(this.sk), 3, s, "inapp", s2);
        }
        catch (Exception ex) {
            gs.d("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            return null;
        }
    }
    
    public void destroy() {
        this.sk = null;
    }
    
    public void r(final IBinder binder) {
        try {
            this.sk = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, binder);
        }
        catch (Exception ex) {
            gs.W("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
        }
    }
}
