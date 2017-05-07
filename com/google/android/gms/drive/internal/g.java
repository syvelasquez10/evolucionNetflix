// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<CreateContentsRequest>
{
    static void a(final CreateContentsRequest createContentsRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, createContentsRequest.BR);
        b.c(parcel, 2, createContentsRequest.MN);
        b.H(parcel, d);
    }
    
    public CreateContentsRequest aa(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        int g2 = 536870912;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new CreateContentsRequest(g, g2);
    }
    
    public CreateContentsRequest[] bj(final int n) {
        return new CreateContentsRequest[n];
    }
}
