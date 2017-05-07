// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.StorageStats;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class as implements Parcelable$Creator<OnStorageStatsResponse>
{
    static void a(final OnStorageStatsResponse onStorageStatsResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onStorageStatsResponse.BR);
        b.a(parcel, 2, (Parcelable)onStorageStatsResponse.Po, n, false);
        b.H(parcel, d);
    }
    
    public OnStorageStatsResponse au(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        StorageStats storageStats = null;
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
                    storageStats = a.a(parcel, b, StorageStats.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new OnStorageStatsResponse(g, storageStats);
    }
    
    public OnStorageStatsResponse[] bG(final int n) {
        return new OnStorageStatsResponse[n];
    }
}
