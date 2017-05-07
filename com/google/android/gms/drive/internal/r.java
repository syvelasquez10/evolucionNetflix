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

public class r implements Parcelable$Creator<OnContentsResponse>
{
    static void a(final OnContentsResponse onContentsResponse, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, onContentsResponse.kg);
        b.a(parcel, 2, (Parcelable)onContentsResponse.qK, n, false);
        b.D(parcel, o);
    }
    
    public OnContentsResponse G(final Parcel parcel) {
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
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OnContentsResponse(g, contents);
    }
    
    public OnContentsResponse[] ag(final int n) {
        return new OnContentsResponse[n];
    }
}
