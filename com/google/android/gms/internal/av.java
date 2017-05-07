// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.location.Location;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class av implements SafeParcelable
{
    public static final aw CREATOR;
    public final Bundle extras;
    public final long nT;
    public final int nU;
    public final List<String> nV;
    public final boolean nW;
    public final int nX;
    public final boolean nY;
    public final String nZ;
    public final bj oa;
    public final Location ob;
    public final String oc;
    public final Bundle od;
    public final int versionCode;
    
    static {
        CREATOR = new aw();
    }
    
    public av(final int versionCode, final long nt, final Bundle extras, final int nu, final List<String> nv, final boolean nw, final int nx, final boolean ny, final String nz, final bj oa, final Location ob, final String oc, final Bundle od) {
        this.versionCode = versionCode;
        this.nT = nt;
        this.extras = extras;
        this.nU = nu;
        this.nV = nv;
        this.nW = nw;
        this.nX = nx;
        this.nY = ny;
        this.nZ = nz;
        this.oa = oa;
        this.ob = ob;
        this.oc = oc;
        this.od = od;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aw.a(this, parcel, n);
    }
}
