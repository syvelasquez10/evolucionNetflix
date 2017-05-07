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

public class f implements Parcelable$Creator<CloseContentsRequest>
{
    static void a(final CloseContentsRequest closeContentsRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, closeContentsRequest.BR);
        b.a(parcel, 2, (Parcelable)closeContentsRequest.NX, n, false);
        b.a(parcel, 3, closeContentsRequest.NZ, false);
        b.H(parcel, d);
    }
    
    public CloseContentsRequest Z(final Parcel parcel) {
        Boolean d = null;
        final int c = a.C(parcel);
        int g = 0;
        Contents contents = null;
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
                    contents = a.a(parcel, b, Contents.CREATOR);
                    continue;
                }
                case 3: {
                    d = a.d(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new CloseContentsRequest(g, contents, d);
    }
    
    public CloseContentsRequest[] bi(final int n) {
        return new CloseContentsRequest[n];
    }
}
