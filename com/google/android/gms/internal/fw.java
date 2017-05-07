// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class fw implements Parcelable$Creator<fv>
{
    static void a(final fv fv, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, fv.getVersionCode());
        b.a(parcel, 2, (Parcelable)fv.eT(), n, false);
        b.F(parcel, p3);
    }
    
    public fv[] S(final int n) {
        return new fv[n];
    }
    
    public fv q(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        fx fx = null;
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
                    fx = a.a(parcel, n, (android.os.Parcelable$Creator<fx>)com.google.android.gms.internal.fx.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new fv(g, fx);
    }
}
