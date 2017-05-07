// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Contents>
{
    static void a(final Contents contents, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, contents.kg);
        b.a(parcel, 2, (Parcelable)contents.om, n, false);
        b.c(parcel, 3, contents.qE);
        b.c(parcel, 4, contents.qF);
        b.a(parcel, 5, (Parcelable)contents.qG, n, false);
        b.D(parcel, o);
    }
    
    public Contents[] Y(final int n) {
        return new Contents[n];
    }
    
    public Contents y(final Parcel parcel) {
        DriveId driveId = null;
        int g = 0;
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g2 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(m)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m);
                    continue;
                }
                case 2: {
                    parcelFileDescriptor = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m);
                    continue;
                }
                case 4: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m);
                    continue;
                }
                case 5: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new Contents(g3, parcelFileDescriptor, g2, g, driveId);
    }
}
