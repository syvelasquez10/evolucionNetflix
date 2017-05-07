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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, applicationMetadata.getVersionCode());
        b.a(parcel, 2, applicationMetadata.getApplicationId(), false);
        b.a(parcel, 3, applicationMetadata.getName(), false);
        b.b(parcel, 4, applicationMetadata.getImages(), false);
        b.a(parcel, 5, applicationMetadata.xK, false);
        b.a(parcel, 6, applicationMetadata.getSenderAppIdentifier(), false);
        b.a(parcel, 7, (Parcelable)applicationMetadata.dz(), n, false);
        b.F(parcel, p3);
    }
    
    public ApplicationMetadata j(final Parcel parcel) {
        Uri uri = null;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        String n = null;
        List<String> a = null;
        List<WebImage> c = null;
        String n2 = null;
        String n3 = null;
        while (parcel.dataPosition() < o) {
            final int n4 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n4)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n4);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n4);
                    continue;
                }
                case 2: {
                    n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n4);
                    continue;
                }
                case 3: {
                    n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n4);
                    continue;
                }
                case 4: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n4, WebImage.CREATOR);
                    continue;
                }
                case 5: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n4);
                    continue;
                }
                case 6: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n4);
                    continue;
                }
                case 7: {
                    uri = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n4, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new ApplicationMetadata(g, n3, n2, c, a, n, uri);
    }
    
    public ApplicationMetadata[] w(final int n) {
        return new ApplicationMetadata[n];
    }
}
