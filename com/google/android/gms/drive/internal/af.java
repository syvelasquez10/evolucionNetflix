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

public class af implements Parcelable$Creator<OnMetadataResponse>
{
    static void a(final OnMetadataResponse onMetadataResponse, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, onMetadataResponse.xH);
        b.a(parcel, 2, (Parcelable)onMetadataResponse.EZ, n, false);
        b.F(parcel, p3);
    }
    
    public OnMetadataResponse T(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
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
                    metadataBundle = a.a(parcel, n, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnMetadataResponse(g, metadataBundle);
    }
    
    public OnMetadataResponse[] ax(final int n) {
        return new OnMetadataResponse[n];
    }
}
