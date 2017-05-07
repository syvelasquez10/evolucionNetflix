// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class eu implements Parcelable$Creator<ev.b>
{
    static void a(final ev.b b, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, b.versionCode);
        b.a(parcel, 2, b.qw, false);
        b.a(parcel, 3, (Parcelable)b.qx, n, false);
        b.D(parcel, o);
    }
    
    public ev.b[] S(final int n) {
        return new ev.b[n];
    }
    
    public ev.b u(final Parcel parcel) {
        SafeParcelable safeParcelable = null;
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
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, i, (android.os.Parcelable$Creator<es.a<?, ?>>)es.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ev.b(g, m, (es.a<?, ?>)safeParcelable);
    }
}
