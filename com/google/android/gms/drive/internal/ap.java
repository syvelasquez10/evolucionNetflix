// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ap implements Parcelable$Creator<OnMetadataResponse>
{
    static void a(final OnMetadataResponse onMetadataResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onMetadataResponse.BR);
        b.a(parcel, 2, (Parcelable)onMetadataResponse.Od, n, false);
        b.H(parcel, d);
    }
    
    public OnMetadataResponse ar(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    metadataBundle = a.a(parcel, b, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new OnMetadataResponse(g, metadataBundle);
    }
    
    public OnMetadataResponse[] bD(final int n) {
        return new OnMetadataResponse[n];
    }
}
