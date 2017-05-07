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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, contents.xH);
        b.a(parcel, 2, (Parcelable)contents.Cj, n, false);
        b.c(parcel, 3, contents.Eu);
        b.c(parcel, 4, contents.Ev);
        b.a(parcel, 5, (Parcelable)contents.Ew, n, false);
        b.F(parcel, p3);
    }
    
    public Contents[] ac(final int n) {
        return new Contents[n];
    }
    
    public Contents y(final Parcel parcel) {
        DriveId driveId = null;
        int g = 0;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g2 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
                case 2: {
                    parcelFileDescriptor = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
                case 4: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
                case 5: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new Contents(g3, parcelFileDescriptor, g2, g, driveId);
    }
}
