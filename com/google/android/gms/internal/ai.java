// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ai implements Parcelable$Creator<ah>
{
    static void a(final ah ah, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, ah.versionCode);
        b.a(parcel, 2, ah.lH);
        b.a(parcel, 3, ah.extras, false);
        b.c(parcel, 4, ah.lI);
        b.a(parcel, 5, ah.lJ, false);
        b.a(parcel, 6, ah.lK);
        b.c(parcel, 7, ah.lL);
        b.a(parcel, 8, ah.lM);
        b.a(parcel, 9, ah.lN, false);
        b.a(parcel, 10, (Parcelable)ah.lO, n, false);
        b.a(parcel, 11, (Parcelable)ah.lP, n, false);
        b.a(parcel, 12, ah.lQ, false);
        b.F(parcel, p3);
    }
    
    public ah a(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        long i = 0L;
        Bundle p = null;
        int g2 = 0;
        List<String> a = null;
        boolean c = false;
        int g3 = 0;
        boolean c2 = false;
        String n = null;
        av av = null;
        Location location = null;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n3)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    i = com.google.android.gms.common.internal.safeparcel.a.i(parcel, n3);
                    continue;
                }
                case 3: {
                    p = com.google.android.gms.common.internal.safeparcel.a.p(parcel, n3);
                    continue;
                }
                case 4: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    continue;
                }
                case 5: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n3);
                    continue;
                }
                case 6: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n3);
                    continue;
                }
                case 7: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    continue;
                }
                case 8: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n3);
                    continue;
                }
                case 9: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n3);
                    continue;
                }
                case 10: {
                    av = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n3, (android.os.Parcelable$Creator<av>)com.google.android.gms.internal.av.CREATOR);
                    continue;
                }
                case 11: {
                    location = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n3, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
                    continue;
                }
                case 12: {
                    n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ah(g, i, p, g2, a, c, g3, c2, n, av, location, n2);
    }
    
    public ah[] b(final int n) {
        return new ah[n];
    }
}
