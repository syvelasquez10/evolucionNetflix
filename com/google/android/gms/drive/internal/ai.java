// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ai implements Parcelable$Creator<OnContentsResponse>
{
    static void a(final OnContentsResponse onContentsResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onContentsResponse.BR);
        b.a(parcel, 2, (Parcelable)onContentsResponse.Op, n, false);
        b.a(parcel, 3, onContentsResponse.Pg);
        b.H(parcel, d);
    }
    
    public OnContentsResponse ak(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        Contents contents = null;
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
                    contents = a.a(parcel, b, Contents.CREATOR);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new OnContentsResponse(g, contents, c);
    }
    
    public OnContentsResponse[] bw(final int n) {
        return new OnContentsResponse[n];
    }
}
