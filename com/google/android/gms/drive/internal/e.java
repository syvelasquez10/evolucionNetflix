// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<CloseContentsRequest>
{
    static void a(final CloseContentsRequest closeContentsRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, closeContentsRequest.xH);
        b.a(parcel, 2, (Parcelable)closeContentsRequest.EX, n, false);
        b.a(parcel, 3, closeContentsRequest.EY, false);
        b.F(parcel, p3);
    }
    
    public CloseContentsRequest F(final Parcel parcel) {
        Boolean d = null;
        final int o = a.o(parcel);
        int g = 0;
        Contents contents = null;
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
                    contents = a.a(parcel, n, Contents.CREATOR);
                    continue;
                }
                case 3: {
                    d = a.d(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CloseContentsRequest(g, contents, d);
    }
    
    public CloseContentsRequest[] aj(final int n) {
        return new CloseContentsRequest[n];
    }
}
