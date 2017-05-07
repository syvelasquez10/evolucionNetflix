// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ob implements Parcelable$Creator<ny$b>
{
    static void a(final ny$b ny$b, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        final Set<Integer> alR = ny$b.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny$b.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, (Parcelable)ny$b.ank, n, true);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, (Parcelable)ny$b.anl, n, true);
        }
        if (alR.contains(4)) {
            b.c(parcel, 4, ny$b.anm);
        }
        b.H(parcel, d);
    }
    
    public ny$b df(final Parcel parcel) {
        ny$b$b ny$b$b = null;
        int g = 0;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        ny$b$a ny$b$a = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    set.add(1);
                    continue;
                }
                case 2: {
                    ny$b$a = a.a(parcel, b, (android.os.Parcelable$Creator<ny$b$a>)com.google.android.gms.internal.ny$b$a.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    ny$b$b = a.a(parcel, b, (android.os.Parcelable$Creator<ny$b$b>)com.google.android.gms.internal.ny$b$b.CREATOR);
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
        return new ny$b(set, g2, ny$b$a, ny$b$b, g);
    }
    
    public ny$b[] eW(final int n) {
        return new ny$b[n];
    }
}
