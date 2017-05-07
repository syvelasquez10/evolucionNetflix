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

public class au implements Parcelable$Creator<OpenContentsRequest>
{
    static void a(final OpenContentsRequest openContentsRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, openContentsRequest.BR);
        b.a(parcel, 2, (Parcelable)openContentsRequest.NV, n, false);
        b.c(parcel, 3, openContentsRequest.MN);
        b.c(parcel, 4, openContentsRequest.Pp);
        b.H(parcel, d);
    }
    
    public OpenContentsRequest aw(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        DriveId driveId = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new OpenContentsRequest(g3, driveId, g2, g);
    }
    
    public OpenContentsRequest[] bI(final int n) {
        return new OpenContentsRequest[n];
    }
}
