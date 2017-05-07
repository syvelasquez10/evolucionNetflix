// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ar implements Parcelable$Creator<OnResourceIdSetResponse>
{
    static void a(final OnResourceIdSetResponse onResourceIdSetResponse, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, onResourceIdSetResponse.getVersionCode());
        b.b(parcel, 2, onResourceIdSetResponse.hX(), false);
        b.H(parcel, d);
    }
    
    public OnResourceIdSetResponse at(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        List<String> c2 = null;
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
                case 2: {
                    c2 = a.C(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new OnResourceIdSetResponse(g, c2);
    }
    
    public OnResourceIdSetResponse[] bF(final int n) {
        return new OnResourceIdSetResponse[n];
    }
}
