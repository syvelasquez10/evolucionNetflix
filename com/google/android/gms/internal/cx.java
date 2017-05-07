// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cx implements SafeParcelable
{
    public static final cy CREATOR;
    public final ApplicationInfo applicationInfo;
    public final String kH;
    public final dx kK;
    public final ak kN;
    public final Bundle pf;
    public final ah pg;
    public final PackageInfo ph;
    public final String pi;
    public final String pj;
    public final String pk;
    public final Bundle pl;
    public final int versionCode;
    
    static {
        CREATOR = new cy();
    }
    
    cx(final int versionCode, final Bundle pf, final ah pg, final ak kn, final String kh, final ApplicationInfo applicationInfo, final PackageInfo ph, final String pi, final String pj, final String pk, final dx kk, final Bundle pl) {
        this.versionCode = versionCode;
        this.pf = pf;
        this.pg = pg;
        this.kN = kn;
        this.kH = kh;
        this.applicationInfo = applicationInfo;
        this.ph = ph;
        this.pi = pi;
        this.pj = pj;
        this.pk = pk;
        this.kK = kk;
        this.pl = pl;
    }
    
    public cx(final Bundle bundle, final ah ah, final ak ak, final String s, final ApplicationInfo applicationInfo, final PackageInfo packageInfo, final String s2, final String s3, final String s4, final dx dx, final Bundle bundle2) {
        this(2, bundle, ah, ak, s, applicationInfo, packageInfo, s2, s3, s4, dx, bundle2);
    }
    
    public cx(final a a, final String s) {
        this(a.pf, a.pg, a.kN, a.kH, a.applicationInfo, a.ph, s, a.pj, a.pk, a.kK, a.pl);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        cy.a(this, parcel, n);
    }
    
    public static final class a
    {
        public final ApplicationInfo applicationInfo;
        public final String kH;
        public final dx kK;
        public final ak kN;
        public final Bundle pf;
        public final ah pg;
        public final PackageInfo ph;
        public final String pj;
        public final String pk;
        public final Bundle pl;
        
        public a(final Bundle pf, final ah pg, final ak kn, final String kh, final ApplicationInfo applicationInfo, final PackageInfo ph, final String pj, final String pk, final dx kk, final Bundle pl) {
            this.pf = pf;
            this.pg = pg;
            this.kN = kn;
            this.kH = kh;
            this.applicationInfo = applicationInfo;
            this.ph = ph;
            this.pj = pj;
            this.pk = pk;
            this.kK = kk;
            this.pl = pl;
        }
    }
}
