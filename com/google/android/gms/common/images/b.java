// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.safeparcel.a$a;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<WebImage>
{
    static void a(final WebImage webImage, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)webImage.getUrl(), n, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public WebImage A(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        Uri uri = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new WebImage(g3, uri, g2, g);
    }
    
    public WebImage[] ax(final int n) {
        return new WebImage[n];
    }
}
