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

public class gf implements Parcelable$Creator<gd.a>
{
    static void a(final gd.a a, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, a.versionCode);
        b.a(parcel, 2, a.className, false);
        b.b(parcel, 3, a.El, false);
        b.F(parcel, p3);
    }
    
    public gd.a[] Y(final int n) {
        return new gd.a[n];
    }
    
    public gd.a w(final Parcel parcel) {
        ArrayList<gd.b> c = null;
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
                    c = a.c(parcel, n2, (android.os.Parcelable$Creator<gd.b>)gd.b.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new gd.a(g, n, c);
    }
}
