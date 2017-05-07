// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class al implements Parcelable$Creator<ak>
{
    static void a(final ak ak, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, ak.versionCode);
        b.a(parcel, 2, ak.lS, false);
        b.c(parcel, 3, ak.height);
        b.c(parcel, 4, ak.heightPixels);
        b.a(parcel, 5, ak.lT);
        b.c(parcel, 6, ak.width);
        b.c(parcel, 7, ak.widthPixels);
        b.a(parcel, 8, ak.lU, n, false);
        b.F(parcel, p3);
    }
    
    public ak b(final Parcel parcel) {
        ak[] array = null;
        int g = 0;
        final int o = a.o(parcel);
        int g2 = 0;
        boolean c = false;
        int g3 = 0;
        int g4 = 0;
        String n = null;
        int g5 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g5 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    g4 = a.g(parcel, n2);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, n2);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, n2);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 8: {
                    array = a.b(parcel, n2, (android.os.Parcelable$Creator<ak>)ak.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ak(g5, n, g4, g3, c, g2, g, array);
    }
    
    public ak[] c(final int n) {
        return new ak[n];
    }
}
