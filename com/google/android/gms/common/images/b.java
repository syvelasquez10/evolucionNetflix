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
        final int p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)webImage.getUrl(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public WebImage[] K(final int n) {
        return new WebImage[n];
    }
    
    public WebImage l(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        Uri uri = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    uri = a.a(parcel, n, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new WebImage(g3, uri, g2, g);
    }
}
