// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ag implements Parcelable$Creator<OnSyncMoreResponse>
{
    static void a(final OnSyncMoreResponse onSyncMoreResponse, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, onSyncMoreResponse.xH);
        b.a(parcel, 2, onSyncMoreResponse.Fg);
        b.F(parcel, p3);
    }
    
    public OnSyncMoreResponse U(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        int g = 0;
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
                    c = a.c(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnSyncMoreResponse(g, c);
    }
    
    public OnSyncMoreResponse[] ay(final int n) {
        return new OnSyncMoreResponse[n];
    }
}
