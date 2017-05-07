// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Asset>
{
    static void a(final Asset asset, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, asset.BR);
        b.a(parcel, 2, asset.getData(), false);
        b.a(parcel, 3, asset.getDigest(), false);
        b.a(parcel, 4, (Parcelable)asset.auG, n, false);
        b.a(parcel, 5, (Parcelable)asset.uri, n, false);
        b.H(parcel, d);
    }
    
    public Asset dP(final Parcel parcel) {
        Uri uri = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        String o = null;
        byte[] r = null;
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
                    r = com.google.android.gms.common.internal.safeparcel.a.r(parcel, b);
                    continue;
                }
                case 3: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    parcelFileDescriptor = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 5: {
                    uri = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new Asset(g, r, o, parcelFileDescriptor, uri);
    }
    
    public Asset[] fR(final int n) {
        return new Asset[n];
    }
}
