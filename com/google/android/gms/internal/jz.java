// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jz implements Parcelable$Creator<jy>
{
    static void a(final jy jy, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, jy.getVersionCode());
        b.a(parcel, 2, jy.adn, false);
        b.a(parcel, 3, jy.pm, false);
        b.a(parcel, 4, (Parcelable)jy.adr, n, false);
        b.a(parcel, 5, (Parcelable)jy.ads, n, false);
        b.a(parcel, 6, (Parcelable)jy.adt, n, false);
        b.F(parcel, p3);
    }
    
    public jy bx(final Parcel parcel) {
        jw jw = null;
        final int o = a.o(parcel);
        int g = 0;
        jw jw2 = null;
        ju ju = null;
        String n = null;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    ju = a.a(parcel, n3, com.google.android.gms.internal.ju.CREATOR);
                    continue;
                }
                case 5: {
                    jw2 = a.a(parcel, n3, com.google.android.gms.internal.jw.CREATOR);
                    continue;
                }
                case 6: {
                    jw = a.a(parcel, n3, com.google.android.gms.internal.jw.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new jy(g, n2, n, ju, jw2, jw);
    }
    
    public jy[] cL(final int n) {
        return new jy[n];
    }
}
