// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<CountrySpecification>
{
    static void a(final CountrySpecification countrySpecification, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, countrySpecification.getVersionCode());
        b.a(parcel, 2, countrySpecification.id, false);
        b.D(parcel, o);
    }
    
    public CountrySpecification aG(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CountrySpecification(g, m);
    }
    
    public CountrySpecification[] by(final int n) {
        return new CountrySpecification[n];
    }
}
