// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jk implements Parcelable$Creator<ji$a>
{
    static void a(final ji$a ji$a, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, ji$a.getVersionCode());
        b.c(parcel, 2, ji$a.hd());
        b.a(parcel, 3, ji$a.hj());
        b.c(parcel, 4, ji$a.he());
        b.a(parcel, 5, ji$a.hk());
        b.a(parcel, 6, ji$a.hl(), false);
        b.c(parcel, 7, ji$a.hm());
        b.a(parcel, 8, ji$a.ho(), false);
        b.a(parcel, 9, (Parcelable)ji$a.hq(), n, false);
        b.H(parcel, d);
    }
    
    public ji$a I(final Parcel parcel) {
        jd jd = null;
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        String o2 = null;
        boolean c2 = false;
        int g2 = 0;
        boolean c3 = false;
        int g3 = 0;
        int g4 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    jd = a.a(parcel, b, (android.os.Parcelable$Creator<jd>)com.google.android.gms.internal.jd.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ji$a(g4, g3, c3, g2, c2, o2, g, o, jd);
    }
    
    public ji$a[] aI(final int n) {
        return new ji$a[n];
    }
}
