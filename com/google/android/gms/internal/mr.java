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

public class mr implements Parcelable$Creator<mq>
{
    static void a(final mq mq, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, mq.getName(), false);
        b.c(parcel, 1000, mq.BR);
        b.a(parcel, 2, (Parcelable)mq.mj(), n, false);
        b.a(parcel, 3, mq.getAddress(), false);
        b.c(parcel, 4, mq.mk(), false);
        b.a(parcel, 5, mq.getPhoneNumber(), false);
        b.a(parcel, 6, mq.ml(), false);
        b.H(parcel, d);
    }
    
    public mq cD(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        String o2 = null;
        List<mo> c2 = null;
        String o3 = null;
        LatLng latLng = null;
        String o4 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<mo>)mo.CREATOR);
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
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new mq(g, o4, latLng, o3, c2, o2, o);
    }
    
    public mq[] es(final int n) {
        return new mq[n];
    }
}
