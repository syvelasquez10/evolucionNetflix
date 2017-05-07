// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<MetadataBundle>
{
    static void a(final MetadataBundle metadataBundle, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, metadataBundle.xH);
        b.a(parcel, 2, metadataBundle.FQ, false);
        b.F(parcel, p3);
    }
    
    public MetadataBundle[] aF(final int n) {
        return new MetadataBundle[n];
    }
    
    public MetadataBundle ab(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        Bundle p = null;
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
                    p = a.p(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new MetadataBundle(g, p);
    }
}
