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
        final int d = b.D(parcel);
        b.c(parcel, 1, contents.BR);
        b.a(parcel, 2, (Parcelable)contents.Kx, n, false);
        b.c(parcel, 3, contents.uQ);
        b.c(parcel, 4, contents.MN);
        b.a(parcel, 5, (Parcelable)contents.MO, n, false);
        b.a(parcel, 7, contents.MP);
        b.H(parcel, d);
    }
    
    public Contents N(final Parcel parcel) {
        DriveId driveId = null;
        boolean c = false;
        final int c2 = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        int g2 = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int g3 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    parcelFileDescriptor = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 5: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
                case 7: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c2, parcel);
        }
        return new Contents(g3, parcelFileDescriptor, g2, g, driveId, c);
    }
    
    public Contents[] aS(final int n) {
        return new Contents[n];
    }
}
