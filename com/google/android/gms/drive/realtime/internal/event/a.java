// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<ObjectChangedDetails>
{
    static void a(final ObjectChangedDetails objectChangedDetails, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, objectChangedDetails.BR);
        b.c(parcel, 2, objectChangedDetails.Rj);
        b.c(parcel, 3, objectChangedDetails.Rk);
        b.H(parcel, d);
    }
    
    public ObjectChangedDetails aY(final Parcel parcel) {
        int g = 0;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new ObjectChangedDetails(g3, g2, g);
    }
    
    public ObjectChangedDetails[] cl(final int n) {
        return new ObjectChangedDetails[n];
    }
}
