// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class v implements Parcelable$Creator<OnMetadataResponse>
{
    static void a(final OnMetadataResponse onMetadataResponse, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, onMetadataResponse.kg);
        b.a(parcel, 2, (Parcelable)onMetadataResponse.qZ, n, false);
        b.D(parcel, o);
    }
    
    public OnMetadataResponse K(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
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
                    metadataBundle = a.a(parcel, m, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OnMetadataResponse(g, metadataBundle);
    }
    
    public OnMetadataResponse[] ak(final int n) {
        return new OnMetadataResponse[n];
    }
}
