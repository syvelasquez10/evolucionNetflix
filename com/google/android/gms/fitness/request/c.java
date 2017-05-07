// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<b>
{
    static void a(final b b, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, b.getDeviceAddress(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, b.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)b.iW(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public b bA(final Parcel parcel) {
        BleDevice bleDevice = null;
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
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    bleDevice = a.a(parcel, b, BleDevice.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new b(g, o, bleDevice);
    }
    
    public b[] cR(final int n) {
        return new b[n];
    }
}
