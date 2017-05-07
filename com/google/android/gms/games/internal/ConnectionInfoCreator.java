// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ConnectionInfoCreator implements Parcelable$Creator<ConnectionInfo>
{
    static void a(final ConnectionInfo connectionInfo, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, connectionInfo.gi(), false);
        b.c(parcel, 1000, connectionInfo.getVersionCode());
        b.c(parcel, 2, connectionInfo.gj());
        b.F(parcel, p3);
    }
    
    public ConnectionInfo[] aW(final int n) {
        return new ConnectionInfo[n];
    }
    
    public ConnectionInfo ap(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        String n = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ConnectionInfo(g2, n, g);
    }
}
