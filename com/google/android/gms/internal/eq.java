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

public class eq implements Parcelable$Creator<ep>
{
    static void a(final ep ep, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, ep.getVersionCode());
        b.b(parcel, 2, ep.cg(), false);
        b.D(parcel, o);
    }
    
    public ep[] P(final int n) {
        return new ep[n];
    }
    
    public ep r(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        ArrayList<ep.a> c = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, m, (android.os.Parcelable$Creator<ep.a>)ep.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ep(g, c);
    }
}
