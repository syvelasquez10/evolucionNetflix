// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class n implements Parcelable$Creator<DisconnectRequest>
{
    static void a(final DisconnectRequest disconnectRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, disconnectRequest.BR);
        b.H(parcel, d);
    }
    
    public DisconnectRequest af(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
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
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new DisconnectRequest(g);
    }
    
    public DisconnectRequest[] bp(final int n) {
        return new DisconnectRequest[n];
    }
}
