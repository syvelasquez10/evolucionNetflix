// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import java.util.List;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<UserAddressRequest>
{
    static void a(final UserAddressRequest userAddressRequest, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, userAddressRequest.getVersionCode());
        b.b(parcel, 2, userAddressRequest.Ny, false);
        b.F(parcel, p3);
    }
    
    public UserAddressRequest ay(final Parcel parcel) {
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        List<CountrySpecification> c = null;
        while (parcel.dataPosition() < o) {
            final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
                case 2: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n, CountrySpecification.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new UserAddressRequest(g, c);
    }
    
    public UserAddressRequest[] bs(final int n) {
        return new UserAddressRequest[n];
    }
}
