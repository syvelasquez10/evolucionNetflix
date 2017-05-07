// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class bz implements SafeParcelable
{
    public static final ca CREATOR;
    public final String adUnitId;
    public final ApplicationInfo applicationInfo;
    public final cu ej;
    public final x em;
    public final Bundle hq;
    public final v hr;
    public final PackageInfo hs;
    public final String ht;
    public final String hu;
    public final String hv;
    public final int versionCode;
    
    static {
        CREATOR = new ca();
    }
    
    bz(final int versionCode, final Bundle hq, final v hr, final x em, final String adUnitId, final ApplicationInfo applicationInfo, final PackageInfo hs, final String ht, final String hu, final String hv, final cu ej) {
        this.versionCode = versionCode;
        this.hq = hq;
        this.hr = hr;
        this.em = em;
        this.adUnitId = adUnitId;
        this.applicationInfo = applicationInfo;
        this.hs = hs;
        this.ht = ht;
        this.hu = hu;
        this.hv = hv;
        this.ej = ej;
    }
    
    public bz(final Bundle bundle, final v v, final x x, final String s, final ApplicationInfo applicationInfo, final PackageInfo packageInfo, final String s2, final String s3, final String s4, final cu cu) {
        this(1, bundle, v, x, s, applicationInfo, packageInfo, s2, s3, s4, cu);
    }
    
    public bz(final a a, final String s) {
        this(a.hq, a.hr, a.em, a.adUnitId, a.applicationInfo, a.hs, s, a.hu, a.hv, a.ej);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ca.a(this, parcel, n);
    }
    
    public static final class a
    {
        public final String adUnitId;
        public final ApplicationInfo applicationInfo;
        public final cu ej;
        public final x em;
        public final Bundle hq;
        public final v hr;
        public final PackageInfo hs;
        public final String hu;
        public final String hv;
        
        public a(final Bundle hq, final v hr, final x em, final String adUnitId, final ApplicationInfo applicationInfo, final PackageInfo hs, final String hu, final String hv, final cu ej) {
            this.hq = hq;
            this.hr = hr;
            this.em = em;
            this.adUnitId = adUnitId;
            this.applicationInfo = applicationInfo;
            this.hs = hs;
            this.hu = hu;
            this.hv = hv;
            this.ej = ej;
        }
    }
}
