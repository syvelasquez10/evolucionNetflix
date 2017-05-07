// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gu implements Parcelable$Creator<gt>
{
    static void a(final gt gt, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, gt.versionCode);
        b.a(parcel, 2, gt.wD, false);
        b.c(parcel, 3, gt.wE);
        b.c(parcel, 4, gt.wF);
        b.a(parcel, 5, gt.wG);
        b.H(parcel, d);
    }
    
    public gt j(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        String o = null;
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new gt(g3, o, g2, g, c);
    }
    
    public gt[] v(final int n) {
        return new gt[n];
    }
}
