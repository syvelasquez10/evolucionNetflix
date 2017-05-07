// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class s implements Parcelable$Creator<OnDownloadProgressResponse>
{
    static void a(final OnDownloadProgressResponse onDownloadProgressResponse, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, onDownloadProgressResponse.kg);
        b.a(parcel, 2, onDownloadProgressResponse.rx);
        b.a(parcel, 3, onDownloadProgressResponse.ry);
        b.D(parcel, o);
    }
    
    public OnDownloadProgressResponse H(final Parcel parcel) {
        long h = 0L;
        final int n = a.n(parcel);
        int g = 0;
        long h2 = 0L;
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
                    h2 = a.h(parcel, m);
                    continue;
                }
                case 3: {
                    h = a.h(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OnDownloadProgressResponse(g, h2, h);
    }
    
    public OnDownloadProgressResponse[] ah(final int n) {
        return new OnDownloadProgressResponse[n];
    }
}
