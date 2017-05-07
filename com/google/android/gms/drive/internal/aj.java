// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aj implements Parcelable$Creator<OnDownloadProgressResponse>
{
    static void a(final OnDownloadProgressResponse onDownloadProgressResponse, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, onDownloadProgressResponse.BR);
        b.a(parcel, 2, onDownloadProgressResponse.Ph);
        b.a(parcel, 3, onDownloadProgressResponse.Pi);
        b.H(parcel, d);
    }
    
    public OnDownloadProgressResponse al(final Parcel parcel) {
        long i = 0L;
        final int c = a.C(parcel);
        int g = 0;
        long j = 0L;
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
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new OnDownloadProgressResponse(g, j, i);
    }
    
    public OnDownloadProgressResponse[] bx(final int n) {
        return new OnDownloadProgressResponse[n];
    }
}
