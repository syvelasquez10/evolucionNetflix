// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class du implements Parcelable$Creator<dv>
{
    static void a(final dv dv, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, dv.versionCode);
        b.a(parcel, 2, dv.cl(), false);
        b.a(parcel, 3, dv.cm(), false);
        b.a(parcel, 4, dv.cn(), false);
        b.a(parcel, 5, dv.co(), false);
        b.H(parcel, d);
    }
    
    public dv g(final Parcel parcel) {
        IBinder p = null;
        final int c = a.C(parcel);
        int g = 0;
        IBinder p2 = null;
        IBinder p3 = null;
        IBinder p4 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    p4 = a.p(parcel, b);
                    continue;
                }
                case 3: {
                    p3 = a.p(parcel, b);
                    continue;
                }
                case 4: {
                    p2 = a.p(parcel, b);
                    continue;
                }
                case 5: {
                    p = a.p(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new dv(g, p4, p3, p2, p);
    }
    
    public dv[] n(final int n) {
        return new dv[n];
    }
}
