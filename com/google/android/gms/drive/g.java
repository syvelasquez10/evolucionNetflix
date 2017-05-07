// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<StorageStats>
{
    static void a(final StorageStats storageStats, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, storageStats.BR);
        b.a(parcel, 2, storageStats.Nt);
        b.a(parcel, 3, storageStats.Nu);
        b.a(parcel, 4, storageStats.Nv);
        b.a(parcel, 5, storageStats.Nw);
        b.c(parcel, 6, storageStats.Nx);
        b.H(parcel, d);
    }
    
    public StorageStats R(final Parcel parcel) {
        int g = 0;
        long i = 0L;
        final int c = a.C(parcel);
        long j = 0L;
        long k = 0L;
        long l = 0L;
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
                    l = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 5: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new StorageStats(g2, l, k, j, i, g);
    }
    
    public StorageStats[] aY(final int n) {
        return new StorageStats[n];
    }
}
