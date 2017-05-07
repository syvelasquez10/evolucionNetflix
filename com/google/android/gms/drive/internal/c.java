// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<CreateContentsRequest>
{
    static void a(final CreateContentsRequest createContentsRequest, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, createContentsRequest.kg);
        b.D(parcel, o);
    }
    
    public CreateContentsRequest B(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CreateContentsRequest(g);
    }
    
    public CreateContentsRequest[] ab(final int n) {
        return new CreateContentsRequest[n];
    }
}
