// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<DriveId>
{
    static void a(final DriveId driveId, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, driveId.BR);
        b.a(parcel, 2, driveId.Na, false);
        b.a(parcel, 3, driveId.Nb);
        b.a(parcel, 4, driveId.Nc);
        b.H(parcel, d);
    }
    
    public DriveId O(final Parcel parcel) {
        long i = 0L;
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        long j = 0L;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new DriveId(g, o, j, i);
    }
    
    public DriveId[] aT(final int n) {
        return new DriveId[n];
    }
}
