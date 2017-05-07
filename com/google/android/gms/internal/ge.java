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

public class ge implements Parcelable$Creator<gd>
{
    static void a(final gd gd, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, gd.getVersionCode());
        b.b(parcel, 2, gd.fn(), false);
        b.a(parcel, 3, gd.fo(), false);
        b.F(parcel, p3);
    }
    
    public gd[] X(final int n) {
        return new gd[n];
    }
    
    public gd v(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        ArrayList<gd.a> c = null;
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
                    c = a.c(parcel, n2, (android.os.Parcelable$Creator<gd.a>)gd.a.CREATOR);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new gd(g, c, n);
    }
}
