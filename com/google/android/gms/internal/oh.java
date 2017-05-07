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

public class oh implements Parcelable$Creator<ny$g>
{
    static void a(final ny$g ny$g, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = ny$g.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny$g.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, ny$g.anv);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, ny$g.mValue, true);
        }
        b.H(parcel, d);
    }
    
    public ny$g dl(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String o = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    set.add(1);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b);
                    set.add(2);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    set.add(3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new ny$g(set, g, c, o);
    }
    
    public ny$g[] fc(final int n) {
        return new ny$g[n];
    }
}
