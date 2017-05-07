// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<WebImage>
{
    static void a(final WebImage webImage, final Parcel parcel, final int n) {
        final int o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)webImage.getUrl(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public WebImage[] G(final int n) {
        return new WebImage[n];
    }
    
    public WebImage k(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        Uri uri = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    uri = a.a(parcel, m, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new WebImage(g3, uri, g2, g);
    }
}
