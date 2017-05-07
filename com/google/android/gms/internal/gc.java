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

public class gc implements Parcelable$Creator<gd.b>
{
    static void a(final gd.b b, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, b.versionCode);
        b.a(parcel, 2, b.eM, false);
        b.a(parcel, 3, (Parcelable)b.Em, n, false);
        b.F(parcel, p3);
    }
    
    public gd.b[] W(final int n) {
        return new gd.b[n];
    }
    
    public gd.b u(final Parcel parcel) {
        SafeParcelable safeParcelable = null;
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
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, n2, (android.os.Parcelable$Creator<ga.a<?, ?>>)ga.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new gd.b(g, n, (ga.a<?, ?>)safeParcelable);
    }
}
