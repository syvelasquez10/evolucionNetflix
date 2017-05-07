// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import android.net.Uri;
import com.google.android.gms.common.images.WebImage;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<ApplicationMetadata>
{
    static void a(final ApplicationMetadata applicationMetadata, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, applicationMetadata.getVersionCode());
        b.a(parcel, 2, applicationMetadata.getApplicationId(), false);
        b.a(parcel, 3, applicationMetadata.getName(), false);
        b.b(parcel, 4, applicationMetadata.getImages(), false);
        b.a(parcel, 5, applicationMetadata.kj, false);
        b.a(parcel, 6, applicationMetadata.getSenderAppIdentifier(), false);
        b.a(parcel, 7, (Parcelable)applicationMetadata.aN(), n, false);
        b.D(parcel, o);
    }
    
    public ApplicationMetadata i(final Parcel parcel) {
        Uri uri = null;
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g = 0;
        String m = null;
        List<String> y = null;
        List<WebImage> c = null;
        String i = null;
        String j = null;
        while (parcel.dataPosition() < n) {
            final int k = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(k)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    continue;
                }
                case 2: {
                    j = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    continue;
                }
                case 3: {
                    i = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    continue;
                }
                case 4: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k, WebImage.CREATOR);
                    continue;
                }
                case 5: {
                    y = com.google.android.gms.common.internal.safeparcel.a.y(parcel, k);
                    continue;
                }
                case 6: {
                    m = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    continue;
                }
                case 7: {
                    uri = com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new ApplicationMetadata(g, j, i, c, y, m, uri);
    }
    
    public ApplicationMetadata[] q(final int n) {
        return new ApplicationMetadata[n];
    }
}
