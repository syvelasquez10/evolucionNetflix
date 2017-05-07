// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ab implements Parcelable$Creator<StartBleScanRequest>
{
    static void a(final StartBleScanRequest startBleScanRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, startBleScanRequest.getDataTypes(), false);
        b.c(parcel, 1000, startBleScanRequest.getVersionCode());
        b.a(parcel, 2, startBleScanRequest.jz(), false);
        b.c(parcel, 3, startBleScanRequest.jA());
        b.H(parcel, d);
    }
    
    public StartBleScanRequest bQ(final Parcel parcel) {
        IBinder p = null;
        int g = 0;
        final int c = a.C(parcel);
        List<DataType> c2 = null;
        int g2 = 0;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    p = a.p(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new StartBleScanRequest(g2, c2, p, g);
    }
    
    public StartBleScanRequest[] di(final int n) {
        return new StartBleScanRequest[n];
    }
}
