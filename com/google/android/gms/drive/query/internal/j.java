// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<Operator>
{
    static void a(final Operator operator, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1000, operator.xH);
        b.a(parcel, 1, operator.mTag, false);
        b.F(parcel, p3);
    }
    
    public Operator[] aQ(final int n) {
        return new Operator[n];
    }
    
    public Operator am(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new Operator(g, n);
    }
}
