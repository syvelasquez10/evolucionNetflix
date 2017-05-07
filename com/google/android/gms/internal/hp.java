// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hp implements Parcelable$Creator<ho>
{
    static void a(final ho ho, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, ho.getName(), false);
        b.c(parcel, 1000, ho.xH);
        b.a(parcel, 2, (Parcelable)ho.ia(), n, false);
        b.a(parcel, 3, ho.getAddress(), false);
        b.b(parcel, 4, ho.ib(), false);
        b.a(parcel, 5, ho.getPhoneNumber(), false);
        b.a(parcel, 6, ho.ic(), false);
        b.F(parcel, p3);
    }
    
    public ho aH(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        String n2 = null;
        List<hm> c = null;
        String n3 = null;
        LatLng latLng = null;
        String n4 = null;
        while (parcel.dataPosition() < o) {
            final int n5 = a.n(parcel);
            switch (a.R(n5)) {
                default: {
                    a.b(parcel, n5);
                    continue;
                }
                case 1: {
                    n4 = a.n(parcel, n5);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n5);
                    continue;
                }
                case 2: {
                    latLng = a.a(parcel, n5, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    n3 = a.n(parcel, n5);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, n5, (android.os.Parcelable$Creator<hm>)hm.CREATOR);
                    continue;
                }
                case 5: {
                    n2 = a.n(parcel, n5);
                    continue;
                }
                case 6: {
                    n = a.n(parcel, n5);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ho(g, n4, latLng, n3, c, n2, n);
    }
    
    public ho[] bI(final int n) {
        return new ho[n];
    }
}
