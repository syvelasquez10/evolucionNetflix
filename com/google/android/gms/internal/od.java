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

public class od implements Parcelable$Creator<ny$b$b>
{
    static void a(final ny$b$b ny$b$b, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = ny$b$b.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny$b$b.BR);
        }
        if (alR.contains(2)) {
            b.c(parcel, 2, ny$b$b.lg);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, ny$b$b.uR, true);
        }
        if (alR.contains(4)) {
            b.c(parcel, 4, ny$b$b.lf);
        }
        b.H(parcel, d);
    }
    
    public ny$b$b dh(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String o = null;
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
                    o = a.o(parcel, b);
                    set.add(3);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    set.add(4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ny$b$b(set, g3, g2, o, g);
    }
    
    public ny$b$b[] eY(final int n) {
        return new ny$b$b[n];
    }
}
