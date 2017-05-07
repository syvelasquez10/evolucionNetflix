// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class r implements Parcelable$Creator<q>
{
    static void a(final q q, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)q.getSession(), n, false);
        b.c(parcel, 1000, q.BR);
        b.a(parcel, 2, (Parcelable)q.iP(), n, false);
        b.H(parcel, d);
    }
    
    public q bv(final Parcel parcel) {
        DataSet set = null;
        final int c = a.C(parcel);
        int g = 0;
        Session session = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    session = a.a(parcel, b, Session.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    set = a.a(parcel, b, DataSet.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new q(g, session, set);
    }
    
    public q[] cM(final int n) {
        return new q[n];
    }
}
