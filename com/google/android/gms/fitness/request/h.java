// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<DataTypeCreateRequest>
{
    static void a(final DataTypeCreateRequest dataTypeCreateRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, dataTypeCreateRequest.getName(), false);
        b.c(parcel, 1000, dataTypeCreateRequest.getVersionCode());
        b.c(parcel, 2, dataTypeCreateRequest.getFields(), false);
        b.H(parcel, d);
    }
    
    public DataTypeCreateRequest bF(final Parcel parcel) {
        List<Field> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        String o = null;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b, Field.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new DataTypeCreateRequest(g, o, c);
    }
    
    public DataTypeCreateRequest[] cW(final int n) {
        return new DataTypeCreateRequest[n];
    }
}
