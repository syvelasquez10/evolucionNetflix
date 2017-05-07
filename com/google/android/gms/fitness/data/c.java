// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<BleDevice>
{
    static void a(final BleDevice bleDevice, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, bleDevice.getAddress(), false);
        b.c(parcel, 1000, bleDevice.getVersionCode());
        b.a(parcel, 2, bleDevice.getName(), false);
        b.b(parcel, 3, bleDevice.getSupportedProfiles(), false);
        b.c(parcel, 4, bleDevice.getDataTypes(), false);
        b.H(parcel, d);
    }
    
    public BleDevice bj(final Parcel parcel) {
        List<DataType> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        List<String> c3 = null;
        String o = null;
        String o2 = null;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o2 = a.o(parcel, b);
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
                case 3: {
                    c3 = a.C(parcel, b);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new BleDevice(g, o2, o, c3, c);
    }
    
    public BleDevice[] cy(final int n) {
        return new BleDevice[n];
    }
}
