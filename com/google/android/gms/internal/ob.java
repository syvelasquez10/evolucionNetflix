// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.people.Person;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ob implements Parcelable$Creator<ny.b>
{
    static void a(final ny.b b, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        final Set<Integer> alR = b.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, b.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, (Parcelable)b.ank, n, true);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, (Parcelable)b.anl, n, true);
        }
        if (alR.contains(4)) {
            b.c(parcel, 4, b.anm);
        }
        b.H(parcel, d);
    }
    
    public ny.b df(final Parcel parcel) {
        Person.Cover.CoverPhoto coverPhoto = null;
        int g = 0;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        Person.Cover.CoverInfo coverInfo = null;
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
                    coverInfo = a.a(parcel, b, (android.os.Parcelable$Creator<ny.b.a>)ny.b.a.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    coverPhoto = a.a(parcel, b, (android.os.Parcelable$Creator<ny.b.b>)ny.b.b.CREATOR);
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
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ny.b(set, g2, (ny.b.a)coverInfo, (ny.b.b)coverPhoto, g);
    }
    
    public ny.b[] eW(final int n) {
        return new ny.b[n];
    }
}
