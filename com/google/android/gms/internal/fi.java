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
    
    public fi(final fi$a fi$a, final String s) {
        this(fi$a.tw, fi$a.tx, fi$a.lH, fi$a.lA, fi$a.applicationInfo, fi$a.ty, s, fi$a.tA, fi$a.tB, fi$a.lD, fi$a.tC, fi$a.tD, fi$a.lS, fi$a.tE, fi$a.tF);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        fj.a(this, parcel, n);
    }
}
