// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<ValuesAddedDetails>
{
    static void a(final ValuesAddedDetails valuesAddedDetails, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, valuesAddedDetails.BR);
        b.c(parcel, 2, valuesAddedDetails.mIndex);
        b.c(parcel, 3, valuesAddedDetails.Rj);
        b.c(parcel, 4, valuesAddedDetails.Rk);
        b.a(parcel, 5, valuesAddedDetails.RF, false);
        b.c(parcel, 6, valuesAddedDetails.RG);
        b.H(parcel, d);
    }
    
    public ValuesAddedDetails bf(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        int g5 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g5 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ValuesAddedDetails(g5, g4, g3, g2, o, g);
    }
    
    public ValuesAddedDetails[] cs(final int n) {
        return new ValuesAddedDetails[n];
    }
}
