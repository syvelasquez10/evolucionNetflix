// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<BeginCompoundOperationRequest>
{
    static void a(final BeginCompoundOperationRequest beginCompoundOperationRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, beginCompoundOperationRequest.BR);
        b.a(parcel, 2, beginCompoundOperationRequest.Ra);
        b.a(parcel, 3, beginCompoundOperationRequest.mName, false);
        b.a(parcel, 4, beginCompoundOperationRequest.Rb);
        b.H(parcel, d);
    }
    
    public BeginCompoundOperationRequest aU(final Parcel parcel) {
        boolean c = false;
        final int c2 = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        String o = null;
        boolean c3 = true;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b);
                    continue;
                }
                case 3: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    c3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c2, parcel);
        }
        return new BeginCompoundOperationRequest(g, c, o, c3);
    }
    
    public BeginCompoundOperationRequest[] cg(final int n) {
        return new BeginCompoundOperationRequest[n];
    }
}
