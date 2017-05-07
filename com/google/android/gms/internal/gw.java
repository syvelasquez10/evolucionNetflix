// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gw implements Parcelable$Creator<gv>
{
    static void a(final gv gv, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, (Parcelable)gv.dS(), n, false);
        b.c(parcel, 1000, gv.kg);
        b.a(parcel, 2, (Parcelable)gv.dT(), n, false);
        b.D(parcel, o);
    }
    
    public gv[] aZ(final int n) {
        return new gv[n];
    }
    
    public gv ak(final Parcel parcel) {
        gt gt = null;
        final int n = a.n(parcel);
        int g = 0;
        LocationRequest locationRequest = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    locationRequest = a.a(parcel, m, (android.os.Parcelable$Creator<LocationRequest>)LocationRequest.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    gt = a.a(parcel, m, (android.os.Parcelable$Creator<gt>)com.google.android.gms.internal.gt.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new gv(g, locationRequest, gt);
    }
}
