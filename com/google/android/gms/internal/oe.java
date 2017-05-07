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

public class oe implements Parcelable$Creator<ny$c>
{
    static void a(final ny$c ny$c, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = ny$c.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny$c.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, ny$c.uR, true);
        }
        b.H(parcel, d);
    }
    
    public ny$c di(final Parcel parcel) {
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String o = null;
        while (parcel.dataPosition() < c) {
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
                    o = a.o(parcel, b);
                    set.add(2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ny$c(set, g, o);
    }
    
    public ny$c[] eZ(final int n) {
        return new ny$c[n];
    }
}
