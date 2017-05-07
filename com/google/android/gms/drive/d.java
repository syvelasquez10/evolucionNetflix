// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<DriveId>
{
    static void a(final DriveId driveId, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, driveId.xH);
        b.a(parcel, 2, driveId.EH, false);
        b.a(parcel, 3, driveId.EI);
        b.a(parcel, 4, driveId.EJ);
        b.F(parcel, p3);
    }
    
    public DriveId[] ad(final int n) {
        return new DriveId[n];
    }
    
    public DriveId z(final Parcel parcel) {
        long i = 0L;
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        long j = 0L;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    j = a.i(parcel, n2);
                    continue;
                }
                case 4: {
                    i = a.i(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new DriveId(g, n, j, i);
    }
}
