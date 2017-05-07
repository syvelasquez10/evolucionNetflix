// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<ValuesSetDetails>
{
    static void a(final ValuesSetDetails valuesSetDetails, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, valuesSetDetails.BR);
        b.c(parcel, 2, valuesSetDetails.mIndex);
        b.c(parcel, 3, valuesSetDetails.Rj);
        b.c(parcel, 4, valuesSetDetails.Rk);
        b.H(parcel, d);
    }
    
    public ValuesSetDetails bh(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ValuesSetDetails(g4, g3, g2, g);
    }
    
    public ValuesSetDetails[] cu(final int n) {
        return new ValuesSetDetails[n];
    }
}
