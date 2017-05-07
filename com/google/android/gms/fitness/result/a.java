// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.BleDevice;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<BleDevicesResult>
{
    static void a(final BleDevicesResult bleDevicesResult, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, bleDevicesResult.getClaimedBleDevices(), false);
        b.c(parcel, 1000, bleDevicesResult.getVersionCode());
        b.a(parcel, 2, (Parcelable)bleDevicesResult.getStatus(), n, false);
        b.H(parcel, d);
    }
    
    public BleDevicesResult bV(final Parcel parcel) {
        Status status = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        List<BleDevice> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, BleDevice.CREATOR);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    status = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new BleDevicesResult(g, c2, status);
    }
    
    public BleDevicesResult[] dn(final int n) {
        return new BleDevicesResult[n];
    }
}
