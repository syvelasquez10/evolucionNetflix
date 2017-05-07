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
    static void a(final DriveId driveId, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, driveId.kg);
        b.a(parcel, 2, driveId.qO, false);
        b.a(parcel, 3, driveId.qP);
        b.a(parcel, 4, driveId.qQ);
        b.D(parcel, o);
    }
    
    public DriveId[] Z(final int n) {
        return new DriveId[n];
    }
    
    public DriveId z(final Parcel parcel) {
        long h = 0L;
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        long h2 = 0L;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    h2 = a.h(parcel, i);
                    continue;
                }
                case 4: {
                    h = a.h(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new DriveId(g, m, h2, h);
    }
}
