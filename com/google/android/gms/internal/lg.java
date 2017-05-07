// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class lg implements Parcelable$Creator<lf>
{
    static void a(final lf lf, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, lf.getDataTypes(), false);
        b.c(parcel, 1000, lf.getVersionCode());
        b.H(parcel, d);
    }
    
    public lf by(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        List<DataType> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new lf(g, c2);
    }
    
    public lf[] cP(final int n) {
        return new lf[n];
    }
}
