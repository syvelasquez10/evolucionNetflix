// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<MetadataBundle>
{
    static void a(final MetadataBundle metadataBundle, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, metadataBundle.BR);
        b.a(parcel, 2, metadataBundle.PD, false);
        b.H(parcel, d);
    }
    
    public MetadataBundle aH(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Bundle q = null;
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
                    q = a.q(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new MetadataBundle(g, q);
    }
    
    public MetadataBundle[] bT(final int n) {
        return new MetadataBundle[n];
    }
}
