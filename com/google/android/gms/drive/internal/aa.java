// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aa implements Parcelable$Creator<OnDownloadProgressResponse>
{
    static void a(final OnDownloadProgressResponse onDownloadProgressResponse, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, onDownloadProgressResponse.xH);
        b.a(parcel, 2, onDownloadProgressResponse.FF);
        b.a(parcel, 3, onDownloadProgressResponse.FG);
        b.F(parcel, p3);
    }
    
    public OnDownloadProgressResponse O(final Parcel parcel) {
        long i = 0L;
        final int o = a.o(parcel);
        int g = 0;
        long j = 0L;
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
                    j = a.i(parcel, n);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnDownloadProgressResponse(g, j, i);
    }
    
    public OnDownloadProgressResponse[] as(final int n) {
        return new OnDownloadProgressResponse[n];
    }
}
