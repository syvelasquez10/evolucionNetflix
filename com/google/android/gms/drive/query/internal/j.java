// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<MatchAllFilter>
{
    static void a(final MatchAllFilter matchAllFilter, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1000, matchAllFilter.BR);
        b.H(parcel, d);
    }
    
    public MatchAllFilter aR(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        while (parcel.dataPosition() < c) {
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
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new MatchAllFilter(g);
    }
    
    public MatchAllFilter[] cd(final int n) {
        return new MatchAllFilter[n];
    }
}
