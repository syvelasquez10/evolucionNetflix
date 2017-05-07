// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents.model;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<CountrySpecification>
{
    static void a(final CountrySpecification countrySpecification, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, countrySpecification.getVersionCode());
        b.a(parcel, 2, countrySpecification.qd, false);
        b.F(parcel, p3);
    }
    
    public CountrySpecification az(final Parcel parcel) {
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        String n = null;
        while (parcel.dataPosition() < o) {
            final int n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n2)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new CountrySpecification(g, n);
    }
    
    public CountrySpecification[] bt(final int n) {
        return new CountrySpecification[n];
    }
}
