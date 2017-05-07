// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class am implements Parcelable$Creator<TrashResourceRequest>
{
    static void a(final TrashResourceRequest trashResourceRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, trashResourceRequest.xH);
        b.a(parcel, 2, (Parcelable)trashResourceRequest.EV, n, false);
        b.F(parcel, p3);
    }
    
    public TrashResourceRequest Z(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, n, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new TrashResourceRequest(g, driveId);
    }
    
    public TrashResourceRequest[] aD(final int n) {
        return new TrashResourceRequest[n];
    }
}
