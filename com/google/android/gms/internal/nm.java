// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class nm implements Parcelable$Creator<nl>
{
    static void a(final nl nl, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, nl.versionCode);
        b.a(parcel, 2, nl.packageName, false);
        b.c(parcel, 3, nl.akG);
        b.c(parcel, 4, nl.akH);
        b.a(parcel, 5, nl.akI, false);
        b.a(parcel, 6, nl.akJ, false);
        b.a(parcel, 7, nl.akK);
        b.H(parcel, d);
    }
    
    public nl cY(final Parcel parcel) {
        String o = null;
        int g = 0;
        final int c = a.C(parcel);
        boolean c2 = true;
        String o2 = null;
        int g2 = 0;
        String o3 = null;
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
                    continue;
                }
                case 2: {
                    o3 = a.o(parcel, b);
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    c2 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new nl(g3, o3, g2, g, o2, o, c2);
    }
    
    public nl[] eO(final int n) {
        return new nl[n];
    }
}
