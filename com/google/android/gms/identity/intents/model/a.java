// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents.model;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<CountrySpecification>
{
    static void a(final CountrySpecification countrySpecification, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, countrySpecification.getVersionCode());
        b.a(parcel, 2, countrySpecification.uW, false);
        b.H(parcel, d);
    }
    
    public CountrySpecification cq(final Parcel parcel) {
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new CountrySpecification(g, o);
    }
    
    public CountrySpecification[] dY(final int n) {
        return new CountrySpecification[n];
    }
}
