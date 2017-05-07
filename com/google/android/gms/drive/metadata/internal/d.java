// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<MetadataBundle>
{
    static void a(final MetadataBundle metadataBundle, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, metadataBundle.kg);
        b.a(parcel, 2, metadataBundle.rF, false);
        b.D(parcel, o);
    }
    
    public MetadataBundle P(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        Bundle o = null;
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
                    o = a.o(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new MetadataBundle(g, o);
    }
    
    public MetadataBundle[] ap(final int n) {
        return new MetadataBundle[n];
    }
}
