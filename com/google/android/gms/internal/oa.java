// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class oa implements Parcelable$Creator<ny.a>
{
    static void a(final ny.a a, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = a.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, a.BR);
        }
        if (alR.contains(2)) {
            b.c(parcel, 2, a.ani);
        }
        if (alR.contains(3)) {
            b.c(parcel, 3, a.anj);
        }
        b.H(parcel, d);
    }
    
    public ny.a de(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    set.add(1);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, b);
                    set.add(2);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    set.add(3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ny.a(set, g3, g2, g);
    }
    
    public ny.a[] eV(final int n) {
        return new ny.a[n];
    }
}
