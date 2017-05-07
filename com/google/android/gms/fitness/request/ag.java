// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ag implements Parcelable$Creator<UnclaimBleDeviceRequest>
{
    static void a(final UnclaimBleDeviceRequest unclaimBleDeviceRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1000, unclaimBleDeviceRequest.getVersionCode());
        b.a(parcel, 2, unclaimBleDeviceRequest.getDeviceAddress(), false);
        b.H(parcel, d);
    }
    
    public UnclaimBleDeviceRequest bT(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new UnclaimBleDeviceRequest(g, o);
    }
    
    public UnclaimBleDeviceRequest[] dl(final int n) {
        return new UnclaimBleDeviceRequest[n];
    }
}
