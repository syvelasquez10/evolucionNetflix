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

public class z implements Parcelable$Creator<OnContentsResponse>
{
    static void a(final OnContentsResponse onContentsResponse, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, onContentsResponse.xH);
        b.a(parcel, 2, (Parcelable)onContentsResponse.EA, n, false);
        b.F(parcel, p3);
    }
    
    public OnContentsResponse N(final Parcel parcel) {
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
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnContentsResponse(g, contents);
    }
    
    public OnContentsResponse[] ar(final int n) {
        return new OnContentsResponse[n];
    }
}
