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

public class oi implements Parcelable$Creator<ny$h>
{
    static void a(final ny$h ny$h, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = ny$h.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny$h.BR);
        }
        if (alR.contains(3)) {
            b.c(parcel, 3, ny$h.nB());
        }
        if (alR.contains(4)) {
            b.a(parcel, 4, ny$h.mValue, true);
        }
        if (alR.contains(5)) {
            b.a(parcel, 5, ny$h.anw, true);
        }
        if (alR.contains(6)) {
            b.c(parcel, 6, ny$h.FD);
        }
        b.H(parcel, d);
    }
    
    public ny$h dm(final Parcel parcel) {
        String o = null;
        int g = 0;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g2 = 0;
        String o2 = null;
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
                case 3: {
                    g = a.g(parcel, b);
                    set.add(3);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    set.add(4);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, b);
                    set.add(5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, b);
                    set.add(6);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ny$h(set, g3, o2, g2, o, g);
    }
    
    public ny$h[] fd(final int n) {
        return new ny$h[n];
    }
}
