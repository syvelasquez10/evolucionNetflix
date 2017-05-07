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

public class jl implements Parcelable$Creator<jm.b>
{
    static void a(final jm.b b, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, b.versionCode);
        b.a(parcel, 2, b.fv, false);
        b.a(parcel, 3, (Parcelable)b.ME, n, false);
        b.H(parcel, d);
    }
    
    public jm.b J(final Parcel parcel) {
        SafeParcelable safeParcelable = null;
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
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, b, (android.os.Parcelable$Creator<ji.a<?, ?>>)ji.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new jm.b(g, o, (ji.a<?, ?>)safeParcelable);
    }
    
    public jm.b[] aJ(final int n) {
        return new jm.b[n];
    }
}
