// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aa implements Parcelable$Creator<z>
{
    static void a(final z z, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, z.versionCode);
        b.c(parcel, 2, z.statusCode);
        b.a(parcel, 3, (Parcelable)z.avq, n, false);
        b.H(parcel, d);
    }
    
    public z ea(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    parcelFileDescriptor = a.a(parcel, b, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new z(g2, g, parcelFileDescriptor);
    }
    
    public z[] gc(final int n) {
        return new z[n];
    }
}
