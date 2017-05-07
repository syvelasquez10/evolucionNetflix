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
    static void a(final CountrySpecification countrySpecification, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, countrySpecification.getVersionCode());
        b.a(parcel, 2, countrySpecification.qd, false);
        b.F(parcel, p3);
    }
    
    public CountrySpecification aZ(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CountrySpecification(g, n);
    }
    
    public CountrySpecification[] cl(final int n) {
        return new CountrySpecification[n];
    }
}
