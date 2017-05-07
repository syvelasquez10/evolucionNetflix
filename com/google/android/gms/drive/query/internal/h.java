// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<Operator>
{
    static void a(final Operator operator, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1000, operator.kg);
        b.a(parcel, 1, operator.mTag, false);
        b.D(parcel, o);
    }
    
    public Operator X(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 1: {
                    m = a.m(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new Operator(g, m);
    }
    
    public Operator[] ax(final int n) {
        return new Operator[n];
    }
}
