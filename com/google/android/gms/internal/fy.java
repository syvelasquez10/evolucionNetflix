// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class fy implements Parcelable$Creator<fx>
{
    static void a(final fx fx, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, fx.getVersionCode());
        b.b(parcel, 2, fx.eV(), false);
        b.F(parcel, p3);
    }
    
    public fx[] T(final int n) {
        return new fx[n];
    }
    
    public fx r(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        ArrayList<fx.a> c = null;
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
                    c = a.c(parcel, n, (android.os.Parcelable$Creator<fx.a>)fx.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new fx(g, c);
    }
}
