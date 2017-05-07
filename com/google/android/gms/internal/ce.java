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

public final class ce implements SafeParcelable
{
    public static final cd CREATOR;
    public final dx kK;
    public final String nO;
    public final cb og;
    public final u oh;
    public final cf oi;
    public final dz oj;
    public final az ok;
    public final String ol;
    public final boolean om;
    public final String on;
    public final ci oo;
    public final int op;
    public final bc oq;
    public final String or;
    public final int orientation;
    public final int versionCode;
    
    static {
        CREATOR = new cd();
    }
    
    ce(final int versionCode, final cb og, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4, final String ol, final boolean om, final String on, final IBinder binder5, final int orientation, final int op, final String no, final dx kk, final IBinder binder6, final String or) {
        this.versionCode = versionCode;
        this.og = og;
        this.oh = e.d(d.a.K(binder));
        this.oi = e.d(d.a.K(binder2));
        this.oj = e.d(d.a.K(binder3));
        this.ok = e.d(d.a.K(binder4));
        this.ol = ol;
        this.om = om;
        this.on = on;
        this.oo = e.d(d.a.K(binder5));
        this.orientation = orientation;
        this.op = op;
        this.nO = no;
        this.kK = kk;
        this.oq = e.d(d.a.K(binder6));
        this.or = or;
    }
    
    public ce(final cb og, final u oh, final cf oi, final ci oo, final dx kk) {
        this.versionCode = 3;
        this.og = og;
        this.oh = oh;
        this.oi = oi;
        this.oj = null;
        this.ok = null;
        this.ol = null;
        this.om = false;
        this.on = null;
        this.oo = oo;
        this.orientation = -1;
        this.op = 4;
        this.nO = null;
        this.kK = kk;
        this.oq = null;
        this.or = null;
    }
    
    public ce(final u oh, final cf oi, final az ok, final ci oo, final dz oj, final boolean om, final int orientation, final String no, final dx kk, final bc oq) {
        this.versionCode = 3;
        this.og = null;
        this.oh = oh;
        this.oi = oi;
        this.oj = oj;
        this.ok = ok;
        this.ol = null;
        this.om = om;
        this.on = null;
        this.oo = oo;
        this.orientation = orientation;
        this.op = 3;
        this.nO = no;
        this.kK = kk;
        this.oq = oq;
        this.or = null;
    }
    
    public ce(final u oh, final cf oi, final az ok, final ci oo, final dz oj, final boolean om, final int orientation, final String on, final String ol, final dx kk, final bc oq) {
        this.versionCode = 3;
        this.og = null;
        this.oh = oh;
        this.oi = oi;
        this.oj = oj;
        this.ok = ok;
        this.ol = ol;
        this.om = om;
        this.on = on;
        this.oo = oo;
        this.orientation = orientation;
        this.op = 3;
        this.nO = null;
        this.kK = kk;
        this.oq = oq;
        this.or = null;
    }
    
    public ce(final u oh, final cf oi, final ci oo, final dz oj, final int orientation, final dx kk, final String or) {
        this.versionCode = 3;
        this.og = null;
        this.oh = oh;
        this.oi = oi;
        this.oj = oj;
        this.ok = null;
        this.ol = null;
        this.om = false;
        this.on = null;
        this.oo = oo;
        this.orientation = orientation;
        this.op = 1;
        this.nO = null;
        this.kK = kk;
        this.oq = null;
        this.or = or;
    }
    
    public ce(final u oh, final cf oi, final ci oo, final dz oj, final boolean om, final int orientation, final dx kk) {
        this.versionCode = 3;
        this.og = null;
        this.oh = oh;
        this.oi = oi;
        this.oj = oj;
        this.ok = null;
        this.ol = null;
        this.om = om;
        this.on = null;
        this.oo = oo;
        this.orientation = orientation;
        this.op = 2;
        this.nO = null;
        this.kK = kk;
        this.oq = null;
        this.or = null;
    }
    
    public static ce a(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(ce.class.getClassLoader());
            return (ce)bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void a(final Intent intent, final ce ce) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", (Parcelable)ce);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }
    
    IBinder aO() {
        return e.h(this.oh).asBinder();
    }
    
    IBinder aP() {
        return e.h(this.oi).asBinder();
    }
    
    IBinder aQ() {
        return e.h(this.oj).asBinder();
    }
    
    IBinder aR() {
        return e.h(this.ok).asBinder();
    }
    
    IBinder aS() {
        return e.h(this.oq).asBinder();
    }
    
    IBinder aT() {
        return e.h(this.oo).asBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        cd.a(this, parcel, n);
    }
}
