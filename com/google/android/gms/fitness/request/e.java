// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<DataInsertRequest>
{
    static void a(final DataInsertRequest dataInsertRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)dataInsertRequest.iP(), n, false);
        b.c(parcel, 1000, dataInsertRequest.getVersionCode());
        b.H(parcel, d);
    }
    
    public DataInsertRequest bC(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DataSet set = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    set = a.a(parcel, b, DataSet.CREATOR);
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
        return new DataInsertRequest(g, set);
    }
    
    public DataInsertRequest[] cT(final int n) {
        return new DataInsertRequest[n];
    }
}
