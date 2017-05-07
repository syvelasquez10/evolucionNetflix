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

public class w implements Parcelable$Creator<OpenContentsRequest>
{
    static void a(final OpenContentsRequest openContentsRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, openContentsRequest.kg);
        b.a(parcel, 2, (Parcelable)openContentsRequest.rr, n, false);
        b.c(parcel, 3, openContentsRequest.qF);
        b.D(parcel, o);
    }
    
    public OpenContentsRequest L(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        DriveId driveId = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, m, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OpenContentsRequest(g2, driveId, g);
    }
    
    public OpenContentsRequest[] al(final int n) {
        return new OpenContentsRequest[n];
    }
}
