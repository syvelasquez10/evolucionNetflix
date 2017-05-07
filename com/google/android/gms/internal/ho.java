// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ho implements Parcelable$Creator<hm$b>
{
    static void a(final hm$b hm$b, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, hm$b.BR);
        b.a(parcel, 1, (Parcelable)hm$b.Ck, n, false);
        b.c(parcel, 2, hm$b.Cl, false);
        b.H(parcel, d);
    }
    
    public hm$b[] N(final int n) {
        return new hm$b[n];
    }
    
    public hm$b q(final Parcel parcel) {
        Object c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        Status status = null;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1: {
                    status = a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b, (android.os.Parcelable$Creator<Object>)hs.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new hm$b(g, status, (List<hs>)c);
    }
}
