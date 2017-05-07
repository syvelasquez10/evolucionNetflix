// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;
import android.content.Context;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class dv implements SafeParcelable
{
    public static final du CREATOR;
    public final el lM;
    public final ee lT;
    public final eg si;
    public final Context sj;
    public final int versionCode;
    
    static {
        CREATOR = new du();
    }
    
    dv(final int versionCode, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4) {
        this.versionCode = versionCode;
        this.lM = e.f(d.a.am(binder));
        this.lT = e.f(d.a.am(binder2));
        this.si = e.f(d.a.am(binder3));
        this.sj = e.f(d.a.am(binder4));
    }
    
    public dv(final eg si, final el lm, final ee lt, final Context sj) {
        this.versionCode = 1;
        this.si = si;
        this.lM = lm;
        this.lT = lt;
        this.sj = sj;
    }
    
    public static void a(final Intent intent, final dv dv) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", (Parcelable)dv);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }
    
    public static dv c(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(dv.class.getClassLoader());
            return (dv)bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    IBinder cl() {
        return e.k(this.lM).asBinder();
    }
    
    IBinder cm() {
        return e.k(this.lT).asBinder();
    }
    
    IBinder cn() {
        return e.k(this.si).asBinder();
    }
    
    IBinder co() {
        return e.k(this.sj).asBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        du.a(this, parcel, n);
    }
}
