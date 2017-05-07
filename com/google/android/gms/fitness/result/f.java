// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.q;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<SessionReadResult>
{
    static void a(final SessionReadResult sessionReadResult, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, sessionReadResult.getSessions(), false);
        b.c(parcel, 1000, sessionReadResult.getVersionCode());
        b.c(parcel, 2, sessionReadResult.jJ(), false);
        b.a(parcel, 3, (Parcelable)sessionReadResult.getStatus(), n, false);
        b.H(parcel, d);
    }
    
    public SessionReadResult ca(final Parcel parcel) {
        Status status = null;
        final int c = a.C(parcel);
        int g = 0;
        List<q> c2 = null;
        List<Session> c3 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c3 = a.c(parcel, b, Session.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, b, q.CREATOR);
                    continue;
                }
                case 3: {
                    status = a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new SessionReadResult(g, c3, c2, status);
    }
    
    public SessionReadResult[] ds(final int n) {
        return new SessionReadResult[n];
    }
}
