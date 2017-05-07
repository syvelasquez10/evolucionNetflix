// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<CloseContentsRequest>
{
    static void a(final CloseContentsRequest closeContentsRequest, final Parcel parcel, final int n) {
        final int o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, closeContentsRequest.kg);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)closeContentsRequest.qX, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, closeContentsRequest.qY, false);
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public CloseContentsRequest A(final Parcel parcel) {
        Boolean d = null;
        final int n = a.n(parcel);
        int g = 0;
        Contents contents = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    contents = a.a(parcel, m, Contents.CREATOR);
                    continue;
                }
                case 3: {
                    d = a.d(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CloseContentsRequest(g, contents, d);
    }
    
    public CloseContentsRequest[] aa(final int n) {
        return new CloseContentsRequest[n];
    }
}
