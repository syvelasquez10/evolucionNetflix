// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<CheckResourceIdsExistRequest>
{
    static void a(final CheckResourceIdsExistRequest checkResourceIdsExistRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, checkResourceIdsExistRequest.getVersionCode());
        b.b(parcel, 2, checkResourceIdsExistRequest.hX(), false);
        b.H(parcel, d);
    }
    
    public CheckResourceIdsExistRequest X(final Parcel parcel) {
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
        return new CheckResourceIdsExistRequest(g, c2);
    }
    
    public CheckResourceIdsExistRequest[] bg(final int n) {
        return new CheckResourceIdsExistRequest[n];
    }
}
