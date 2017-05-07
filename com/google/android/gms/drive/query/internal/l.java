// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class l implements Parcelable$Creator<Operator>
{
    static void a(final Operator operator, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1000, operator.BR);
        b.a(parcel, 1, operator.mTag, false);
        b.H(parcel, d);
    }
    
    public Operator aT(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new Operator(g, o);
    }
    
    public Operator[] cf(final int n) {
        return new Operator[n];
    }
}
