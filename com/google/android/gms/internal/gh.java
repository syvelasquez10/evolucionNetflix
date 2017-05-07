// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gh implements Parcelable$Creator<gg>
{
    static void a(final gg gg, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, gg.getVersionCode());
        b.a(parcel, 2, gg.fq(), false);
        b.a(parcel, 3, (Parcelable)gg.fr(), n, false);
        b.F(parcel, p3);
    }
    
    public gg[] Z(final int n) {
        return new gg[n];
    }
    
    public gg x(final Parcel parcel) {
        gd gd = null;
        final int o = a.o(parcel);
        int g = 0;
        Parcel b = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    b = a.B(parcel, n);
                    continue;
                }
                case 3: {
                    gd = a.a(parcel, n, (android.os.Parcelable$Creator<gd>)com.google.android.gms.internal.gd.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new gg(g, b, gd);
    }
}
