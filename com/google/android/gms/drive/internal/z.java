// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class z implements Parcelable$Creator<GetDriveIdFromUniqueIdentifierRequest>
{
    static void a(final GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, getDriveIdFromUniqueIdentifierRequest.BR);
        b.a(parcel, 2, getDriveIdFromUniqueIdentifierRequest.OZ, false);
        b.a(parcel, 3, getDriveIdFromUniqueIdentifierRequest.Pa);
        b.H(parcel, d);
    }
    
    public GetDriveIdFromUniqueIdentifierRequest ag(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        String o = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
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
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new GetDriveIdFromUniqueIdentifierRequest(g, o, c);
    }
    
    public GetDriveIdFromUniqueIdentifierRequest[] bs(final int n) {
        return new GetDriveIdFromUniqueIdentifierRequest[n];
    }
}
