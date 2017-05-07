// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class oc implements Parcelable$Creator<ny$b$a>
{
    static void a(final ny$b$a ny$b$a, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = ny$b$a.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny$b$a.BR);
        }
        if (alR.contains(2)) {
            b.c(parcel, 2, ny$b$a.ann);
        }
        if (alR.contains(3)) {
            b.c(parcel, 3, ny$b$a.ano);
        }
        b.H(parcel, d);
    }
    
    public ny$b$a dg(final Parcel parcel) {
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
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ny$b$a(set, g3, g2, g);
    }
    
    public ny$b$a[] eX(final int n) {
        return new ny$b$a[n];
    }
}
