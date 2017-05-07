// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.b;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class bm implements SafeParcelable
{
    public static final bl CREATOR;
    public final cu ej;
    public final bj gG;
    public final q gH;
    public final bn gI;
    public final cw gJ;
    public final al gK;
    public final String gL;
    public final boolean gM;
    public final String gN;
    public final bq gO;
    public final int gP;
    public final String go;
    public final int orientation;
    public final int versionCode;
    
    static {
        CREATOR = new bl();
    }
    
    bm(final int versionCode, final bj gg, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4, final String gl, final boolean gm, final String gn, final IBinder binder5, final int orientation, final int gp, final String go, final cu ej) {
        this.versionCode = versionCode;
        this.gG = gg;
        this.gH = c.b(b.a.E(binder));
        this.gI = c.b(b.a.E(binder2));
        this.gJ = c.b(b.a.E(binder3));
        this.gK = c.b(b.a.E(binder4));
        this.gL = gl;
        this.gM = gm;
        this.gN = gn;
        this.gO = c.b(b.a.E(binder5));
        this.orientation = orientation;
        this.gP = gp;
        this.go = go;
        this.ej = ej;
    }
    
    public bm(final bj gg, final q gh, final bn gi, final bq go, final cu ej) {
        this.versionCode = 1;
        this.gG = gg;
        this.gH = gh;
        this.gI = gi;
        this.gJ = null;
        this.gK = null;
        this.gL = null;
        this.gM = false;
        this.gN = null;
        this.gO = go;
        this.orientation = -1;
        this.gP = 4;
        this.go = null;
        this.ej = ej;
    }
    
    public bm(final q gh, final bn gi, final al gk, final bq go, final cw gj, final boolean gm, final int orientation, final String go2, final cu ej) {
        this.versionCode = 1;
        this.gG = null;
        this.gH = gh;
        this.gI = gi;
        this.gJ = gj;
        this.gK = gk;
        this.gL = null;
        this.gM = gm;
        this.gN = null;
        this.gO = go;
        this.orientation = orientation;
        this.gP = 3;
        this.go = go2;
        this.ej = ej;
    }
    
    public bm(final q gh, final bn gi, final al gk, final bq go, final cw gj, final boolean gm, final int orientation, final String gn, final String gl, final cu ej) {
        this.versionCode = 1;
        this.gG = null;
        this.gH = gh;
        this.gI = gi;
        this.gJ = gj;
        this.gK = gk;
        this.gL = gl;
        this.gM = gm;
        this.gN = gn;
        this.gO = go;
        this.orientation = orientation;
        this.gP = 3;
        this.go = null;
        this.ej = ej;
    }
    
    public bm(final q gh, final bn gi, final bq go, final cw gj, final int orientation, final cu ej) {
        this.versionCode = 1;
        this.gG = null;
        this.gH = gh;
        this.gI = gi;
        this.gJ = gj;
        this.gK = null;
        this.gL = null;
        this.gM = false;
        this.gN = null;
        this.gO = go;
        this.orientation = orientation;
        this.gP = 1;
        this.go = null;
        this.ej = ej;
    }
    
    public bm(final q gh, final bn gi, final bq go, final cw gj, final boolean gm, final int orientation, final cu ej) {
        this.versionCode = 1;
        this.gG = null;
        this.gH = gh;
        this.gI = gi;
        this.gJ = gj;
        this.gK = null;
        this.gL = null;
        this.gM = gm;
        this.gN = null;
        this.gO = go;
        this.orientation = orientation;
        this.gP = 2;
        this.go = null;
        this.ej = ej;
    }
    
    public static bm a(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(bm.class.getClassLoader());
            return (bm)bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void a(final Intent intent, final bm bm) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", (Parcelable)bm);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }
    
    IBinder aa() {
        return c.h(this.gH).asBinder();
    }
    
    IBinder ab() {
        return c.h(this.gI).asBinder();
    }
    
    IBinder ac() {
        return c.h(this.gJ).asBinder();
    }
    
    IBinder ad() {
        return c.h(this.gK).asBinder();
    }
    
    IBinder ae() {
        return c.h(this.gO).asBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        bl.a(this, parcel, n);
    }
}
