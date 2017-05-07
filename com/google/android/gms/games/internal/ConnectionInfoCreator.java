// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ConnectionInfoCreator implements Parcelable$Creator<ConnectionInfo>
{
    static void a(final ConnectionInfo connectionInfo, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, connectionInfo.jU(), false);
        b.c(parcel, 1000, connectionInfo.getVersionCode());
        b.c(parcel, 2, connectionInfo.jV());
        b.H(parcel, d);
    }
    
    public ConnectionInfo cf(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ConnectionInfo(g2, o, g);
    }
    
    public ConnectionInfo[] dA(final int n) {
        return new ConnectionInfo[n];
    }
}
