// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class at implements Parcelable$Creator<OnSyncMoreResponse>
{
    static void a(final OnSyncMoreResponse onSyncMoreResponse, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, onSyncMoreResponse.BR);
        b.a(parcel, 2, onSyncMoreResponse.Or);
        b.H(parcel, d);
    }
    
    public OnSyncMoreResponse av(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
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
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new OnSyncMoreResponse(g, c);
    }
    
    public OnSyncMoreResponse[] bH(final int n) {
        return new OnSyncMoreResponse[n];
    }
}
