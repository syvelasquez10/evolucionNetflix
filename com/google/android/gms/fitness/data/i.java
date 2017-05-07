// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<Device>
{
    static void a(final Device device, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, device.getManufacturer(), false);
        b.c(parcel, 1000, device.getVersionCode());
        b.a(parcel, 2, device.getModel(), false);
        b.a(parcel, 3, device.getVersion(), false);
        b.a(parcel, 4, device.iN(), false);
        b.c(parcel, 5, device.getType());
        b.H(parcel, d);
    }
    
    public Device bp(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        String o2 = null;
        String o3 = null;
        String o4 = null;
        int g2 = 0;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new Device(g2, o4, o3, o2, o, g);
    }
    
    public Device[] cG(final int n) {
        return new Device[n];
    }
}
