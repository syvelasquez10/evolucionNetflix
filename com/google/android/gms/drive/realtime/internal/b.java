// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<EndCompoundOperationRequest>
{
    static void a(final EndCompoundOperationRequest endCompoundOperationRequest, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, endCompoundOperationRequest.BR);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public EndCompoundOperationRequest aV(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new EndCompoundOperationRequest(g);
    }
    
    public EndCompoundOperationRequest[] ch(final int n) {
        return new EndCompoundOperationRequest[n];
    }
}
