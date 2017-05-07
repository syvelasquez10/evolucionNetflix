// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class fi implements SafeParcelable
{
    public static final fj CREATOR;
    public final ApplicationInfo applicationInfo;
    public final String lA;
    public final gt lD;
    public final ay lH;
    public final List<String> lS;
    public final String tA;
    public final String tB;
    public final Bundle tC;
    public final int tD;
    public final Bundle tE;
    public final boolean tF;
    public final Bundle tw;
    public final av tx;
    public final PackageInfo ty;
    public final String tz;
    public final int versionCode;
    
    static {
        CREATOR = new fj();
    }
    
    fi(final int versionCode, final Bundle tw, final av tx, final ay lh, final String la, final ApplicationInfo applicationInfo, final PackageInfo ty, final String tz, final String ta, final String tb, final gt ld, final Bundle tc, final int td, final List<String> ls, final Bundle te, final boolean tf) {
        this.versionCode = versionCode;
        this.tw = tw;
        this.tx = tx;
        this.lH = lh;
        this.lA = la;
        this.applicationInfo = applicationInfo;
        this.ty = ty;
        this.tz = tz;
        this.tA = ta;
        this.tB = tb;
        this.lD = ld;
        this.tC = tc;
        this.tD = td;
        this.lS = ls;
        this.tE = te;
        this.tF = tf;
    }
    
    public fi(final Bundle bundle, final av av, final ay ay, final String s, final ApplicationInfo applicationInfo, final PackageInfo packageInfo, final String s2, final String s3, final String s4, final gt gt, final Bundle bundle2, final int n, final List<String> list, final Bundle bundle3, final boolean b) {
        this(4, bundle, av, ay, s, applicationInfo, packageInfo, s2, s3, s4, gt, bundle2, n, list, bundle3, b);
    }
    
    public fi(final a a, final String s) {
        this(a.tw, a.tx, a.lH, a.lA, a.applicationInfo, a.ty, s, a.tA, a.tB, a.lD, a.tC, a.tD, a.lS, a.tE, a.tF);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        fj.a(this, parcel, n);
    }
    
    @ez
    public static final class a
    {
        public final ApplicationInfo applicationInfo;
        public final String lA;
        public final gt lD;
        public final ay lH;
        public final List<String> lS;
        public final String tA;
        public final String tB;
        public final Bundle tC;
        public final int tD;
        public final Bundle tE;
        public final boolean tF;
        public final Bundle tw;
        public final av tx;
        public final PackageInfo ty;
        
        public a(final Bundle tw, final av tx, final ay lh, final String la, final ApplicationInfo applicationInfo, final PackageInfo ty, final String ta, final String tb, final gt ld, final Bundle tc, final List<String> ls, final Bundle te, final boolean tf) {
            this.tw = tw;
            this.tx = tx;
            this.lH = lh;
            this.lA = la;
            this.applicationInfo = applicationInfo;
            this.ty = ty;
            this.tA = ta;
            this.tB = tb;
            this.lD = ld;
            this.tC = tc;
            this.tF = tf;
            if (ls != null && ls.size() > 0) {
                this.tD = 2;
                this.lS = ls;
            }
            else {
                this.tD = 0;
                this.lS = null;
            }
            this.tE = te;
        }
    }
}
