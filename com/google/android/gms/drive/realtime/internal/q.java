// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class q implements Parcelable$Creator<ParcelableIndexReference>
{
    static void a(final ParcelableIndexReference parcelableIndexReference, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, parcelableIndexReference.BR);
        b.a(parcel, 2, parcelableIndexReference.Rh, false);
        b.c(parcel, 3, parcelableIndexReference.mIndex);
        b.a(parcel, 4, parcelableIndexReference.Ri);
        b.H(parcel, d);
    }
    
    public ParcelableIndexReference aX(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        String o = null;
        int g = 0;
        int g2 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new ParcelableIndexReference(g2, o, g, c);
    }
    
    public ParcelableIndexReference[] ck(final int n) {
        return new ParcelableIndexReference[n];
    }
}
