// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class m implements Parcelable$Creator<l>
{
    static void a(final l l, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)l.getDataType(), n, false);
        b.c(parcel, 1000, l.getVersionCode());
        b.H(parcel, d);
    }
    
    public l bH(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DataType dataType = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    dataType = a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new l(g, dataType);
    }
    
    public l[] cY(final int n) {
        return new l[n];
    }
}
