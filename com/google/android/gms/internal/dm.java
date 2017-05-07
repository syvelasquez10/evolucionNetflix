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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class dm implements SafeParcelable
{
    public static final dl CREATOR;
    public final gt lD;
    public final int orientation;
    public final dj rK;
    public final t rL;
    public final dn rM;
    public final gv rN;
    public final bw rO;
    public final String rP;
    public final boolean rQ;
    public final String rR;
    public final dq rS;
    public final int rT;
    public final bz rU;
    public final String rV;
    public final x rW;
    public final String rq;
    public final int versionCode;
    
    static {
        CREATOR = new dl();
    }
    
    dm(final int versionCode, final dj rk, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4, final String rp, final boolean rq, final String rr, final IBinder binder5, final int orientation, final int rt, final String rq2, final gt ld, final IBinder binder6, final String rv, final x rw) {
        this.versionCode = versionCode;
        this.rK = rk;
        this.rL = e.f(d.a.am(binder));
        this.rM = e.f(d.a.am(binder2));
        this.rN = e.f(d.a.am(binder3));
        this.rO = e.f(d.a.am(binder4));
        this.rP = rp;
        this.rQ = rq;
        this.rR = rr;
        this.rS = e.f(d.a.am(binder5));
        this.orientation = orientation;
        this.rT = rt;
        this.rq = rq2;
        this.lD = ld;
        this.rU = e.f(d.a.am(binder6));
        this.rV = rv;
        this.rW = rw;
    }
    
    public dm(final dj rk, final t rl, final dn rm, final dq rs, final gt ld) {
        this.versionCode = 4;
        this.rK = rk;
        this.rL = rl;
        this.rM = rm;
        this.rN = null;
        this.rO = null;
        this.rP = null;
        this.rQ = false;
        this.rR = null;
        this.rS = rs;
        this.orientation = -1;
        this.rT = 4;
        this.rq = null;
        this.lD = ld;
        this.rU = null;
        this.rV = null;
        this.rW = null;
    }
    
    public dm(final t rl, final dn rm, final bw ro, final dq rs, final gv rn, final boolean rq, final int orientation, final String rq2, final gt ld, final bz ru) {
        this.versionCode = 4;
        this.rK = null;
        this.rL = rl;
        this.rM = rm;
        this.rN = rn;
        this.rO = ro;
        this.rP = null;
        this.rQ = rq;
        this.rR = null;
        this.rS = rs;
        this.orientation = orientation;
        this.rT = 3;
        this.rq = rq2;
        this.lD = ld;
        this.rU = ru;
        this.rV = null;
        this.rW = null;
    }
    
    public dm(final t rl, final dn rm, final bw ro, final dq rs, final gv rn, final boolean rq, final int orientation, final String rr, final String rp, final gt ld, final bz ru) {
        this.versionCode = 4;
        this.rK = null;
        this.rL = rl;
        this.rM = rm;
        this.rN = rn;
        this.rO = ro;
        this.rP = rp;
        this.rQ = rq;
        this.rR = rr;
        this.rS = rs;
        this.orientation = orientation;
        this.rT = 3;
        this.rq = null;
        this.lD = ld;
        this.rU = ru;
        this.rV = null;
        this.rW = null;
    }
    
    public dm(final t rl, final dn rm, final dq rs, final gv rn, final int orientation, final gt ld, final String rv, final x rw) {
        this.versionCode = 4;
        this.rK = null;
        this.rL = rl;
        this.rM = rm;
        this.rN = rn;
        this.rO = null;
        this.rP = null;
        this.rQ = false;
        this.rR = null;
        this.rS = rs;
        this.orientation = orientation;
        this.rT = 1;
        this.rq = null;
        this.lD = ld;
        this.rU = null;
        this.rV = rv;
        this.rW = rw;
    }
    
    public dm(final t rl, final dn rm, final dq rs, final gv rn, final boolean rq, final int orientation, final gt ld) {
        this.versionCode = 4;
        this.rK = null;
        this.rL = rl;
        this.rM = rm;
        this.rN = rn;
        this.rO = null;
        this.rP = null;
        this.rQ = rq;
        this.rR = null;
        this.rS = rs;
        this.orientation = orientation;
        this.rT = 2;
        this.rq = null;
        this.lD = ld;
        this.rU = null;
        this.rV = null;
        this.rW = null;
    }
    
    public static void a(final Intent intent, final dm dm) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", (Parcelable)dm);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }
    
    public static dm b(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(dm.class.getClassLoader());
            return (dm)bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    IBinder cc() {
        return e.k(this.rL).asBinder();
    }
    
    IBinder cd() {
        return e.k(this.rM).asBinder();
    }
    
    IBinder ce() {
        return e.k(this.rN).asBinder();
    }
    
    IBinder cf() {
        return e.k(this.rO).asBinder();
    }
    
    IBinder cg() {
        return e.k(this.rU).asBinder();
    }
    
    IBinder ch() {
        return e.k(this.rS).asBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        dl.a(this, parcel, n);
    }
}
