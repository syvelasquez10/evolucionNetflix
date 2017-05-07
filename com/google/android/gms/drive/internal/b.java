// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<AuthorizeAccessRequest>
{
    static void a(final AuthorizeAccessRequest authorizeAccessRequest, final Parcel parcel, final int n) {
        final int p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, authorizeAccessRequest.xH);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, authorizeAccessRequest.EU);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, (Parcelable)authorizeAccessRequest.Ew, n, false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public AuthorizeAccessRequest D(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        long i = 0L;
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
                    i = a.i(parcel, n);
                    continue;
                }
                case 3: {
                    driveId = a.a(parcel, n, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new AuthorizeAccessRequest(g, i, driveId);
    }
    
    public AuthorizeAccessRequest[] ah(final int n) {
        return new AuthorizeAccessRequest[n];
    }
}
