// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class y implements Parcelable$Creator<x>
{
    static void a(final x x, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, x.versionCode);
        b.a(parcel, 2, x.eF, false);
        b.c(parcel, 3, x.height);
        b.c(parcel, 4, x.heightPixels);
        b.a(parcel, 5, x.eG);
        b.c(parcel, 6, x.width);
        b.c(parcel, 7, x.widthPixels);
        b.a(parcel, 8, x.eH, n, false);
        b.D(parcel, o);
    }
    
    public x b(final Parcel parcel) {
        x[] array = null;
        int g = 0;
        final int n = a.n(parcel);
        int g2 = 0;
        boolean c = false;
        int g3 = 0;
        int g4 = 0;
        String m = null;
        int g5 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g5 = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    g4 = a.g(parcel, i);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, i);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, i);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 8: {
                    array = a.b(parcel, i, (android.os.Parcelable$Creator<x>)x.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new x(g5, m, g4, g3, c, g2, g, array);
    }
    
    public x[] c(final int n) {
        return new x[n];
    }
}
