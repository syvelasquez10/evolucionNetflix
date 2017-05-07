// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<DataTypeResult>
{
    static void a(final DataTypeResult dataTypeResult, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)dataTypeResult.getStatus(), n, false);
        b.c(parcel, 1000, dataTypeResult.getVersionCode());
        b.a(parcel, 3, (Parcelable)dataTypeResult.getDataType(), n, false);
        b.H(parcel, d);
    }
    
    public DataTypeResult bY(final Parcel parcel) {
        DataType dataType = null;
        final int c = a.C(parcel);
        int g = 0;
        Status status = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    status = a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    dataType = a.a(parcel, b, DataType.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new DataTypeResult(g, status, dataType);
    }
    
    public DataTypeResult[] dq(final int n) {
        return new DataTypeResult[n];
    }
}
