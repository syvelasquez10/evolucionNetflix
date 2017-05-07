// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ma implements Parcelable$Creator<lz>
{
    static void a(final lz lz, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)lz.Ux, n, false);
        b.c(parcel, 1000, lz.getVersionCode());
        b.a(parcel, 2, lz.aeX);
        b.a(parcel, 3, lz.aeY);
        b.a(parcel, 4, lz.aeZ);
        b.c(parcel, 5, lz.afa, false);
        b.a(parcel, 6, lz.mTag, false);
        b.H(parcel, d);
    }
    
    public lz cv(final Parcel parcel) {
        String o = null;
        boolean c = true;
        boolean c2 = false;
        final int c3 = a.C(parcel);
        Object o2 = lz.aeW;
        boolean c4 = true;
        LocationRequest locationRequest = null;
        int g = 0;
        while (parcel.dataPosition() < c3) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    locationRequest = a.a(parcel, b, (android.os.Parcelable$Creator<LocationRequest>)LocationRequest.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 3: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 5: {
                    o2 = a.c(parcel, b, (android.os.Parcelable$Creator<Object>)lr.CREATOR);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c3) {
            throw new a$a("Overread allowed size end=" + c3, parcel);
        }
        return new lz(g, locationRequest, c2, c4, c, (List<lr>)o2, o);
    }
    
    public lz[] ei(final int n) {
        return new lz[n];
    }
}
