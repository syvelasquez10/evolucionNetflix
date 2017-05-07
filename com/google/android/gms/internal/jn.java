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

public class jn implements Parcelable$Creator<jm>
{
    static void a(final jm jm, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, jm.getVersionCode());
        b.a(parcel, 2, jm.add, false);
        b.a(parcel, 3, jm.ade, false);
        b.b(parcel, 4, jm.adf, false);
        b.F(parcel, p3);
    }
    
    public jm br(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        ArrayList<jk> list = gi.fs();
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    list = a.c(parcel, n3, jk.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new jm(g, n2, n, list);
    }
    
    public jm[] cF(final int n) {
        return new jm[n];
    }
}
