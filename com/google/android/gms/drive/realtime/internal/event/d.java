// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<ReferenceShiftedDetails>
{
    static void a(final ReferenceShiftedDetails referenceShiftedDetails, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, referenceShiftedDetails.BR);
        b.a(parcel, 2, referenceShiftedDetails.Rz, false);
        b.a(parcel, 3, referenceShiftedDetails.RA, false);
        b.c(parcel, 4, referenceShiftedDetails.RB);
        b.c(parcel, 5, referenceShiftedDetails.RC);
        b.H(parcel, d);
    }
    
    public ReferenceShiftedDetails bb(final Parcel parcel) {
        String o = null;
        int g = 0;
        final int c = a.C(parcel);
        int g2 = 0;
        String o2 = null;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ReferenceShiftedDetails(g3, o2, o, g2, g);
    }
    
    public ReferenceShiftedDetails[] co(final int n) {
        return new ReferenceShiftedDetails[n];
    }
}
