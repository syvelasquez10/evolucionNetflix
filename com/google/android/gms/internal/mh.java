// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mh implements Parcelable$Creator<mg>
{
    static void a(final mg mg, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, mg.ma());
        b.c(parcel, 1000, mg.getVersionCode());
        b.c(parcel, 2, mg.me());
        b.a(parcel, 3, (Parcelable)mg.mf(), n, false);
        b.H(parcel, d);
    }
    
    public mg cy(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        int g2 = -1;
        mi mi = null;
        int g3 = 0;
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
                case 1000: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    mi = a.a(parcel, b, (android.os.Parcelable$Creator<mi>)com.google.android.gms.internal.mi.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new mg(g3, g, g2, mi);
    }
    
    public mg[] en(final int n) {
        return new mg[n];
    }
}
