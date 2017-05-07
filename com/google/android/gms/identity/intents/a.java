// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<UserAddressRequest>
{
    static void a(final UserAddressRequest userAddressRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, userAddressRequest.getVersionCode());
        b.c(parcel, 2, userAddressRequest.adz, false);
        b.H(parcel, d);
    }
    
    public UserAddressRequest cp(final Parcel parcel) {
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        List<CountrySpecification> c2 = null;
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
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, CountrySpecification.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new UserAddressRequest(g, c2);
    }
    
    public UserAddressRequest[] dX(final int n) {
        return new UserAddressRequest[n];
    }
}
