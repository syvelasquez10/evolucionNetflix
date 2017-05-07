// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import android.net.Uri;
import com.google.android.gms.common.images.WebImage;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<ApplicationMetadata>
{
    static void a(final ApplicationMetadata applicationMetadata, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, applicationMetadata.getVersionCode());
        b.a(parcel, 2, applicationMetadata.getApplicationId(), false);
        b.a(parcel, 3, applicationMetadata.getName(), false);
        b.c(parcel, 4, applicationMetadata.getImages(), false);
        b.b(parcel, 5, applicationMetadata.EB, false);
        b.a(parcel, 6, applicationMetadata.getSenderAppIdentifier(), false);
        b.a(parcel, 7, (Parcelable)applicationMetadata.fv(), n, false);
        b.H(parcel, d);
    }
    
    public ApplicationMetadata[] U(final int n) {
        return new ApplicationMetadata[n];
    }
    
    public ApplicationMetadata t(final Parcel parcel) {
        Uri uri = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        String o = null;
        List<String> c2 = null;
        List<WebImage> c3 = null;
        String o2 = null;
        String o3 = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    c3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b, WebImage.CREATOR);
                    continue;
                }
                case 5: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.C(parcel, b);
                    continue;
                }
                case 6: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 7: {
                    uri = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ApplicationMetadata(g, o3, o2, c3, c2, o, uri);
    }
}
